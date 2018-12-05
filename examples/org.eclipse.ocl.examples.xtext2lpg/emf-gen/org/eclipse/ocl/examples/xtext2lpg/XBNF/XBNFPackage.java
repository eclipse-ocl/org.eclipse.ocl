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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/OCL/Import ecore='http://www.eclipse.org/emf/2002/Ecore'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore invocationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot' settingDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot' validationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot'"
 * @generated
 */
public interface XBNFPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "XBNF";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/ocl/XBNF";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "xbnf";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	XBNFPackage eINSTANCE = org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.AbstractElementImpl <em>Abstract Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.AbstractElementImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getAbstractElement()
	 * @generated
	 */
	int ABSTRACT_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ELEMENT__DEBUG = 0;

	/**
	 * The feature id for the '<em><b>Parent Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ELEMENT__PARENT_RULE = 1;

	/**
	 * The number of structural features of the '<em>Abstract Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ELEMENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Abstract Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.AbstractRuleImpl <em>Abstract Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.AbstractRuleImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getAbstractRule()
	 * @generated
	 */
	int ABSTRACT_RULE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_RULE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_RULE__ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_RULE__DEBUG = 2;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_RULE__KIND = 3;

	/**
	 * The number of structural features of the '<em>Abstract Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_RULE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Abstract Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_RULE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.AssignmentImpl <em>Assignment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.AssignmentImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getAssignment()
	 * @generated
	 */
	int ASSIGNMENT = 3;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__DEBUG = ABSTRACT_ELEMENT__DEBUG;

	/**
	 * The feature id for the '<em><b>Parent Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__PARENT_RULE = ABSTRACT_ELEMENT__PARENT_RULE;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__FEATURE = ABSTRACT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT__OPERATOR = ABSTRACT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_FEATURE_COUNT = ABSTRACT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSIGNMENT_OPERATION_COUNT = ABSTRACT_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.ActionAssignmentImpl <em>Action Assignment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.ActionAssignmentImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getActionAssignment()
	 * @generated
	 */
	int ACTION_ASSIGNMENT = 2;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_ASSIGNMENT__DEBUG = ASSIGNMENT__DEBUG;

	/**
	 * The feature id for the '<em><b>Parent Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_ASSIGNMENT__PARENT_RULE = ASSIGNMENT__PARENT_RULE;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_ASSIGNMENT__FEATURE = ASSIGNMENT__FEATURE;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_ASSIGNMENT__OPERATOR = ASSIGNMENT__OPERATOR;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_ASSIGNMENT__TYPE = ASSIGNMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Action Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_ASSIGNMENT_FEATURE_COUNT = ASSIGNMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Action Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_ASSIGNMENT_OPERATION_COUNT = ASSIGNMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.CharacterRangeImpl <em>Character Range</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.CharacterRangeImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getCharacterRange()
	 * @generated
	 */
	int CHARACTER_RANGE = 4;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_RANGE__DEBUG = ABSTRACT_ELEMENT__DEBUG;

	/**
	 * The feature id for the '<em><b>Parent Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_RANGE__PARENT_RULE = ABSTRACT_ELEMENT__PARENT_RULE;

	/**
	 * The feature id for the '<em><b>Left</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_RANGE__LEFT = ABSTRACT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Right</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_RANGE__RIGHT = ABSTRACT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Character Range</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_RANGE_FEATURE_COUNT = ABSTRACT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Character Range</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_RANGE_OPERATION_COUNT = ABSTRACT_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.ConjunctionImpl <em>Conjunction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.ConjunctionImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getConjunction()
	 * @generated
	 */
	int CONJUNCTION = 5;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONJUNCTION__DEBUG = ABSTRACT_ELEMENT__DEBUG;

	/**
	 * The feature id for the '<em><b>Parent Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONJUNCTION__PARENT_RULE = ABSTRACT_ELEMENT__PARENT_RULE;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONJUNCTION__ELEMENTS = ABSTRACT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Conjunction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONJUNCTION_FEATURE_COUNT = ABSTRACT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Conjunction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONJUNCTION_OPERATION_COUNT = ABSTRACT_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.DisjunctionImpl <em>Disjunction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.DisjunctionImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getDisjunction()
	 * @generated
	 */
	int DISJUNCTION = 6;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISJUNCTION__DEBUG = ABSTRACT_ELEMENT__DEBUG;

	/**
	 * The feature id for the '<em><b>Parent Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISJUNCTION__PARENT_RULE = ABSTRACT_ELEMENT__PARENT_RULE;

	/**
	 * The feature id for the '<em><b>Conjunctions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISJUNCTION__CONJUNCTIONS = ABSTRACT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Disjunction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISJUNCTION_FEATURE_COUNT = ABSTRACT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Disjunction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISJUNCTION_OPERATION_COUNT = ABSTRACT_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.EOFImpl <em>EOF</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.EOFImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getEOF()
	 * @generated
	 */
	int EOF = 7;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOF__DEBUG = ABSTRACT_ELEMENT__DEBUG;

	/**
	 * The feature id for the '<em><b>Parent Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOF__PARENT_RULE = ABSTRACT_ELEMENT__PARENT_RULE;

	/**
	 * The number of structural features of the '<em>EOF</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOF_FEATURE_COUNT = ABSTRACT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>EOF</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOF_OPERATION_COUNT = ABSTRACT_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.EpsilonImpl <em>Epsilon</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.EpsilonImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getEpsilon()
	 * @generated
	 */
	int EPSILON = 8;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPSILON__DEBUG = ABSTRACT_ELEMENT__DEBUG;

	/**
	 * The feature id for the '<em><b>Parent Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPSILON__PARENT_RULE = ABSTRACT_ELEMENT__PARENT_RULE;

	/**
	 * The number of structural features of the '<em>Epsilon</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPSILON_FEATURE_COUNT = ABSTRACT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Epsilon</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EPSILON_OPERATION_COUNT = ABSTRACT_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.GrammarImpl <em>Grammar</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.GrammarImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getGrammar()
	 * @generated
	 */
	int GRAMMAR = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAMMAR__NAME = 0;

	/**
	 * The feature id for the '<em><b>Syntax</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAMMAR__SYNTAX = 1;

	/**
	 * The feature id for the '<em><b>Goals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAMMAR__GOALS = 2;

	/**
	 * The feature id for the '<em><b>Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAMMAR__RULES = 3;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAMMAR__DEBUG = 4;

	/**
	 * The number of structural features of the '<em>Grammar</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAMMAR_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Grammar</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAMMAR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.KeywordImpl <em>Keyword</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.KeywordImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getKeyword()
	 * @generated
	 */
	int KEYWORD = 10;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEYWORD__DEBUG = ABSTRACT_ELEMENT__DEBUG;

	/**
	 * The feature id for the '<em><b>Parent Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEYWORD__PARENT_RULE = ABSTRACT_ELEMENT__PARENT_RULE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEYWORD__VALUE = ABSTRACT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Keyword</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEYWORD_FEATURE_COUNT = ABSTRACT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Keyword</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEYWORD_OPERATION_COUNT = ABSTRACT_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.KeywordAssignmentImpl <em>Keyword Assignment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.KeywordAssignmentImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getKeywordAssignment()
	 * @generated
	 */
	int KEYWORD_ASSIGNMENT = 11;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEYWORD_ASSIGNMENT__DEBUG = ASSIGNMENT__DEBUG;

	/**
	 * The feature id for the '<em><b>Parent Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEYWORD_ASSIGNMENT__PARENT_RULE = ASSIGNMENT__PARENT_RULE;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEYWORD_ASSIGNMENT__FEATURE = ASSIGNMENT__FEATURE;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEYWORD_ASSIGNMENT__OPERATOR = ASSIGNMENT__OPERATOR;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEYWORD_ASSIGNMENT__VALUE = ASSIGNMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Keyword Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEYWORD_ASSIGNMENT_FEATURE_COUNT = ASSIGNMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Keyword Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KEYWORD_ASSIGNMENT_OPERATION_COUNT = ASSIGNMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.LexerGrammarImpl <em>Lexer Grammar</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.LexerGrammarImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getLexerGrammar()
	 * @generated
	 */
	int LEXER_GRAMMAR = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEXER_GRAMMAR__NAME = GRAMMAR__NAME;

	/**
	 * The feature id for the '<em><b>Syntax</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEXER_GRAMMAR__SYNTAX = GRAMMAR__SYNTAX;

	/**
	 * The feature id for the '<em><b>Goals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEXER_GRAMMAR__GOALS = GRAMMAR__GOALS;

	/**
	 * The feature id for the '<em><b>Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEXER_GRAMMAR__RULES = GRAMMAR__RULES;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEXER_GRAMMAR__DEBUG = GRAMMAR__DEBUG;

	/**
	 * The number of structural features of the '<em>Lexer Grammar</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEXER_GRAMMAR_FEATURE_COUNT = GRAMMAR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Lexer Grammar</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEXER_GRAMMAR_OPERATION_COUNT = GRAMMAR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.NegatedTokenImpl <em>Negated Token</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.NegatedTokenImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getNegatedToken()
	 * @generated
	 */
	int NEGATED_TOKEN = 13;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEGATED_TOKEN__DEBUG = ABSTRACT_ELEMENT__DEBUG;

	/**
	 * The feature id for the '<em><b>Parent Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEGATED_TOKEN__PARENT_RULE = ABSTRACT_ELEMENT__PARENT_RULE;

	/**
	 * The feature id for the '<em><b>Terminal</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEGATED_TOKEN__TERMINAL = ABSTRACT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Negated Token</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEGATED_TOKEN_FEATURE_COUNT = ABSTRACT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Negated Token</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEGATED_TOKEN_OPERATION_COUNT = ABSTRACT_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.ParserGrammarImpl <em>Parser Grammar</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.ParserGrammarImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getParserGrammar()
	 * @generated
	 */
	int PARSER_GRAMMAR = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARSER_GRAMMAR__NAME = GRAMMAR__NAME;

	/**
	 * The feature id for the '<em><b>Syntax</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARSER_GRAMMAR__SYNTAX = GRAMMAR__SYNTAX;

	/**
	 * The feature id for the '<em><b>Goals</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARSER_GRAMMAR__GOALS = GRAMMAR__GOALS;

	/**
	 * The feature id for the '<em><b>Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARSER_GRAMMAR__RULES = GRAMMAR__RULES;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARSER_GRAMMAR__DEBUG = GRAMMAR__DEBUG;

	/**
	 * The number of structural features of the '<em>Parser Grammar</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARSER_GRAMMAR_FEATURE_COUNT = GRAMMAR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Parser Grammar</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARSER_GRAMMAR_OPERATION_COUNT = GRAMMAR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.TypedRuleImpl <em>Typed Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.TypedRuleImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getTypedRule()
	 * @generated
	 */
	int TYPED_RULE = 20;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_RULE__NAME = ABSTRACT_RULE__NAME;

	/**
	 * The feature id for the '<em><b>Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_RULE__ELEMENT = ABSTRACT_RULE__ELEMENT;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_RULE__DEBUG = ABSTRACT_RULE__DEBUG;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_RULE__KIND = ABSTRACT_RULE__KIND;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_RULE__TYPE = ABSTRACT_RULE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Subrules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_RULE__SUBRULES = ABSTRACT_RULE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Grammar</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_RULE__GRAMMAR = ABSTRACT_RULE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Typed Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_RULE_FEATURE_COUNT = ABSTRACT_RULE_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Typed Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_RULE_OPERATION_COUNT = ABSTRACT_RULE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.ParserRuleImpl <em>Parser Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.ParserRuleImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getParserRule()
	 * @generated
	 */
	int PARSER_RULE = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARSER_RULE__NAME = TYPED_RULE__NAME;

	/**
	 * The feature id for the '<em><b>Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARSER_RULE__ELEMENT = TYPED_RULE__ELEMENT;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARSER_RULE__DEBUG = TYPED_RULE__DEBUG;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARSER_RULE__KIND = TYPED_RULE__KIND;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARSER_RULE__TYPE = TYPED_RULE__TYPE;

	/**
	 * The feature id for the '<em><b>Subrules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARSER_RULE__SUBRULES = TYPED_RULE__SUBRULES;

	/**
	 * The feature id for the '<em><b>Grammar</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARSER_RULE__GRAMMAR = TYPED_RULE__GRAMMAR;

	/**
	 * The number of structural features of the '<em>Parser Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARSER_RULE_FEATURE_COUNT = TYPED_RULE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Parser Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARSER_RULE_OPERATION_COUNT = TYPED_RULE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.RuleCallImpl <em>Rule Call</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.RuleCallImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getRuleCall()
	 * @generated
	 */
	int RULE_CALL = 16;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_CALL__DEBUG = ABSTRACT_ELEMENT__DEBUG;

	/**
	 * The feature id for the '<em><b>Parent Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_CALL__PARENT_RULE = ABSTRACT_ELEMENT__PARENT_RULE;

	/**
	 * The feature id for the '<em><b>Referred Rule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_CALL__REFERRED_RULE = ABSTRACT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_CALL__NAME = ABSTRACT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Rule Call</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_CALL_FEATURE_COUNT = ABSTRACT_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Rule Call</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_CALL_OPERATION_COUNT = ABSTRACT_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.RuleCallAssignmentImpl <em>Rule Call Assignment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.RuleCallAssignmentImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getRuleCallAssignment()
	 * @generated
	 */
	int RULE_CALL_ASSIGNMENT = 17;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_CALL_ASSIGNMENT__DEBUG = ASSIGNMENT__DEBUG;

	/**
	 * The feature id for the '<em><b>Parent Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_CALL_ASSIGNMENT__PARENT_RULE = ASSIGNMENT__PARENT_RULE;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_CALL_ASSIGNMENT__FEATURE = ASSIGNMENT__FEATURE;

	/**
	 * The feature id for the '<em><b>Operator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_CALL_ASSIGNMENT__OPERATOR = ASSIGNMENT__OPERATOR;

	/**
	 * The feature id for the '<em><b>Referred Rule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_CALL_ASSIGNMENT__REFERRED_RULE = ASSIGNMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_CALL_ASSIGNMENT__NAME = ASSIGNMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Rule Call Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_CALL_ASSIGNMENT_FEATURE_COUNT = ASSIGNMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Rule Call Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_CALL_ASSIGNMENT_OPERATION_COUNT = ASSIGNMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.SyntaxImpl <em>Syntax</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.SyntaxImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getSyntax()
	 * @generated
	 */
	int SYNTAX = 18;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNTAX__NAME = 0;

	/**
	 * The feature id for the '<em><b>Grammars</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNTAX__GRAMMARS = 1;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNTAX__DEBUG = 2;

	/**
	 * The number of structural features of the '<em>Syntax</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNTAX_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Syntax</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNTAX_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.TerminalRuleImpl <em>Terminal Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.TerminalRuleImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getTerminalRule()
	 * @generated
	 */
	int TERMINAL_RULE = 19;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERMINAL_RULE__NAME = TYPED_RULE__NAME;

	/**
	 * The feature id for the '<em><b>Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERMINAL_RULE__ELEMENT = TYPED_RULE__ELEMENT;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERMINAL_RULE__DEBUG = TYPED_RULE__DEBUG;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERMINAL_RULE__KIND = TYPED_RULE__KIND;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERMINAL_RULE__TYPE = TYPED_RULE__TYPE;

	/**
	 * The feature id for the '<em><b>Subrules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERMINAL_RULE__SUBRULES = TYPED_RULE__SUBRULES;

	/**
	 * The feature id for the '<em><b>Grammar</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERMINAL_RULE__GRAMMAR = TYPED_RULE__GRAMMAR;

	/**
	 * The number of structural features of the '<em>Terminal Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERMINAL_RULE_FEATURE_COUNT = TYPED_RULE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Terminal Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERMINAL_RULE_OPERATION_COUNT = TYPED_RULE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.UntilTokenImpl <em>Until Token</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.UntilTokenImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getUntilToken()
	 * @generated
	 */
	int UNTIL_TOKEN = 21;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNTIL_TOKEN__DEBUG = ABSTRACT_ELEMENT__DEBUG;

	/**
	 * The feature id for the '<em><b>Parent Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNTIL_TOKEN__PARENT_RULE = ABSTRACT_ELEMENT__PARENT_RULE;

	/**
	 * The feature id for the '<em><b>Terminal</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNTIL_TOKEN__TERMINAL = ABSTRACT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Until Token</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNTIL_TOKEN_FEATURE_COUNT = ABSTRACT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Until Token</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNTIL_TOKEN_OPERATION_COUNT = ABSTRACT_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.UntypedRuleImpl <em>Untyped Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.UntypedRuleImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getUntypedRule()
	 * @generated
	 */
	int UNTYPED_RULE = 22;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNTYPED_RULE__NAME = ABSTRACT_RULE__NAME;

	/**
	 * The feature id for the '<em><b>Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNTYPED_RULE__ELEMENT = ABSTRACT_RULE__ELEMENT;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNTYPED_RULE__DEBUG = ABSTRACT_RULE__DEBUG;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNTYPED_RULE__KIND = ABSTRACT_RULE__KIND;

	/**
	 * The feature id for the '<em><b>Typed Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNTYPED_RULE__TYPED_RULE = ABSTRACT_RULE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Untyped Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNTYPED_RULE_FEATURE_COUNT = ABSTRACT_RULE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Untyped Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNTYPED_RULE_OPERATION_COUNT = ABSTRACT_RULE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.WildcardImpl <em>Wildcard</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.WildcardImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getWildcard()
	 * @generated
	 */
	int WILDCARD = 23;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WILDCARD__DEBUG = ABSTRACT_ELEMENT__DEBUG;

	/**
	 * The feature id for the '<em><b>Parent Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WILDCARD__PARENT_RULE = ABSTRACT_ELEMENT__PARENT_RULE;

	/**
	 * The number of structural features of the '<em>Wildcard</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WILDCARD_FEATURE_COUNT = ABSTRACT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Wildcard</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WILDCARD_OPERATION_COUNT = ABSTRACT_ELEMENT_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractElement <em>Abstract Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Element</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractElement
	 * @generated
	 */
	EClass getAbstractElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractElement#getDebug <em>Debug</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Debug</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractElement#getDebug()
	 * @see #getAbstractElement()
	 * @generated
	 */
	EAttribute getAbstractElement_Debug();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractElement#getParentRule <em>Parent Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Parent Rule</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractElement#getParentRule()
	 * @see #getAbstractElement()
	 * @generated
	 */
	EReference getAbstractElement_ParentRule();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractRule <em>Abstract Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Rule</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractRule
	 * @generated
	 */
	EClass getAbstractRule();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractRule#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractRule#getName()
	 * @see #getAbstractRule()
	 * @generated
	 */
	EAttribute getAbstractRule_Name();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractRule#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Element</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractRule#getElement()
	 * @see #getAbstractRule()
	 * @generated
	 */
	EReference getAbstractRule_Element();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractRule#getDebug <em>Debug</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Debug</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractRule#getDebug()
	 * @see #getAbstractRule()
	 * @generated
	 */
	EAttribute getAbstractRule_Debug();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractRule#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractRule#getKind()
	 * @see #getAbstractRule()
	 * @generated
	 */
	EAttribute getAbstractRule_Kind();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.ActionAssignment <em>Action Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Assignment</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.ActionAssignment
	 * @generated
	 */
	EClass getActionAssignment();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.ActionAssignment#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.ActionAssignment#getType()
	 * @see #getActionAssignment()
	 * @generated
	 */
	EReference getActionAssignment_Type();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Assignment <em>Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assignment</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.Assignment
	 * @generated
	 */
	EClass getAssignment();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Assignment#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Feature</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.Assignment#getFeature()
	 * @see #getAssignment()
	 * @generated
	 */
	EReference getAssignment_Feature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Assignment#getOperator <em>Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Operator</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.Assignment#getOperator()
	 * @see #getAssignment()
	 * @generated
	 */
	EAttribute getAssignment_Operator();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.CharacterRange <em>Character Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Character Range</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.CharacterRange
	 * @generated
	 */
	EClass getCharacterRange();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.CharacterRange#getLeft <em>Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Left</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.CharacterRange#getLeft()
	 * @see #getCharacterRange()
	 * @generated
	 */
	EAttribute getCharacterRange_Left();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.CharacterRange#getRight <em>Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Right</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.CharacterRange#getRight()
	 * @see #getCharacterRange()
	 * @generated
	 */
	EAttribute getCharacterRange_Right();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Conjunction <em>Conjunction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Conjunction</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.Conjunction
	 * @generated
	 */
	EClass getConjunction();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Conjunction#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.Conjunction#getElements()
	 * @see #getConjunction()
	 * @generated
	 */
	EReference getConjunction_Elements();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Disjunction <em>Disjunction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Disjunction</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.Disjunction
	 * @generated
	 */
	EClass getDisjunction();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Disjunction#getConjunctions <em>Conjunctions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Conjunctions</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.Disjunction#getConjunctions()
	 * @see #getDisjunction()
	 * @generated
	 */
	EReference getDisjunction_Conjunctions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.EOF <em>EOF</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EOF</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.EOF
	 * @generated
	 */
	EClass getEOF();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Epsilon <em>Epsilon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Epsilon</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.Epsilon
	 * @generated
	 */
	EClass getEpsilon();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar <em>Grammar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Grammar</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar
	 * @generated
	 */
	EClass getGrammar();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar#getName()
	 * @see #getGrammar()
	 * @generated
	 */
	EAttribute getGrammar_Name();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar#getSyntax <em>Syntax</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Syntax</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar#getSyntax()
	 * @see #getGrammar()
	 * @generated
	 */
	EReference getGrammar_Syntax();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar#getGoals <em>Goals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Goals</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar#getGoals()
	 * @see #getGrammar()
	 * @generated
	 */
	EReference getGrammar_Goals();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar#getRules <em>Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rules</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar#getRules()
	 * @see #getGrammar()
	 * @generated
	 */
	EReference getGrammar_Rules();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar#getDebug <em>Debug</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Debug</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar#getDebug()
	 * @see #getGrammar()
	 * @generated
	 */
	EAttribute getGrammar_Debug();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Keyword <em>Keyword</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Keyword</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.Keyword
	 * @generated
	 */
	EClass getKeyword();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Keyword#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.Keyword#getValue()
	 * @see #getKeyword()
	 * @generated
	 */
	EAttribute getKeyword_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.KeywordAssignment <em>Keyword Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Keyword Assignment</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.KeywordAssignment
	 * @generated
	 */
	EClass getKeywordAssignment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.LexerGrammar <em>Lexer Grammar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Lexer Grammar</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.LexerGrammar
	 * @generated
	 */
	EClass getLexerGrammar();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.NegatedToken <em>Negated Token</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Negated Token</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.NegatedToken
	 * @generated
	 */
	EClass getNegatedToken();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.NegatedToken#getTerminal <em>Terminal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Terminal</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.NegatedToken#getTerminal()
	 * @see #getNegatedToken()
	 * @generated
	 */
	EReference getNegatedToken_Terminal();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.ParserGrammar <em>Parser Grammar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parser Grammar</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.ParserGrammar
	 * @generated
	 */
	EClass getParserGrammar();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.ParserRule <em>Parser Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parser Rule</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.ParserRule
	 * @generated
	 */
	EClass getParserRule();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.RuleCall <em>Rule Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rule Call</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.RuleCall
	 * @generated
	 */
	EClass getRuleCall();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.RuleCall#getReferredRule <em>Referred Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Rule</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.RuleCall#getReferredRule()
	 * @see #getRuleCall()
	 * @generated
	 */
	EReference getRuleCall_ReferredRule();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.RuleCall#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.RuleCall#getName()
	 * @see #getRuleCall()
	 * @generated
	 */
	EAttribute getRuleCall_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.RuleCallAssignment <em>Rule Call Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rule Call Assignment</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.RuleCallAssignment
	 * @generated
	 */
	EClass getRuleCallAssignment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Syntax <em>Syntax</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Syntax</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.Syntax
	 * @generated
	 */
	EClass getSyntax();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Syntax#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.Syntax#getName()
	 * @see #getSyntax()
	 * @generated
	 */
	EAttribute getSyntax_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Syntax#getGrammars <em>Grammars</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Grammars</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.Syntax#getGrammars()
	 * @see #getSyntax()
	 * @generated
	 */
	EReference getSyntax_Grammars();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Syntax#getDebug <em>Debug</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Debug</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.Syntax#getDebug()
	 * @see #getSyntax()
	 * @generated
	 */
	EAttribute getSyntax_Debug();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.TerminalRule <em>Terminal Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Terminal Rule</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.TerminalRule
	 * @generated
	 */
	EClass getTerminalRule();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.TypedRule <em>Typed Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Typed Rule</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.TypedRule
	 * @generated
	 */
	EClass getTypedRule();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.TypedRule#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.TypedRule#getType()
	 * @see #getTypedRule()
	 * @generated
	 */
	EReference getTypedRule_Type();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.TypedRule#getSubrules <em>Subrules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Subrules</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.TypedRule#getSubrules()
	 * @see #getTypedRule()
	 * @generated
	 */
	EReference getTypedRule_Subrules();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.TypedRule#getGrammar <em>Grammar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Grammar</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.TypedRule#getGrammar()
	 * @see #getTypedRule()
	 * @generated
	 */
	EReference getTypedRule_Grammar();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.UntilToken <em>Until Token</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Until Token</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.UntilToken
	 * @generated
	 */
	EClass getUntilToken();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.UntilToken#getTerminal <em>Terminal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Terminal</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.UntilToken#getTerminal()
	 * @see #getUntilToken()
	 * @generated
	 */
	EReference getUntilToken_Terminal();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.UntypedRule <em>Untyped Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Untyped Rule</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.UntypedRule
	 * @generated
	 */
	EClass getUntypedRule();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.UntypedRule#getTypedRule <em>Typed Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Typed Rule</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.UntypedRule#getTypedRule()
	 * @see #getUntypedRule()
	 * @generated
	 */
	EReference getUntypedRule_TypedRule();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Wildcard <em>Wildcard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wildcard</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.Wildcard
	 * @generated
	 */
	EClass getWildcard();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	XBNFFactory getXBNFFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.AbstractElementImpl <em>Abstract Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.AbstractElementImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getAbstractElement()
		 * @generated
		 */
		EClass ABSTRACT_ELEMENT = eINSTANCE.getAbstractElement();

		/**
		 * The meta object literal for the '<em><b>Debug</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_ELEMENT__DEBUG = eINSTANCE.getAbstractElement_Debug();

		/**
		 * The meta object literal for the '<em><b>Parent Rule</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_ELEMENT__PARENT_RULE = eINSTANCE.getAbstractElement_ParentRule();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.AbstractRuleImpl <em>Abstract Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.AbstractRuleImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getAbstractRule()
		 * @generated
		 */
		EClass ABSTRACT_RULE = eINSTANCE.getAbstractRule();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_RULE__NAME = eINSTANCE.getAbstractRule_Name();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_RULE__ELEMENT = eINSTANCE.getAbstractRule_Element();

		/**
		 * The meta object literal for the '<em><b>Debug</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_RULE__DEBUG = eINSTANCE.getAbstractRule_Debug();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ABSTRACT_RULE__KIND = eINSTANCE.getAbstractRule_Kind();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.ActionAssignmentImpl <em>Action Assignment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.ActionAssignmentImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getActionAssignment()
		 * @generated
		 */
		EClass ACTION_ASSIGNMENT = eINSTANCE.getActionAssignment();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION_ASSIGNMENT__TYPE = eINSTANCE.getActionAssignment_Type();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.AssignmentImpl <em>Assignment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.AssignmentImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getAssignment()
		 * @generated
		 */
		EClass ASSIGNMENT = eINSTANCE.getAssignment();

		/**
		 * The meta object literal for the '<em><b>Feature</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSIGNMENT__FEATURE = eINSTANCE.getAssignment_Feature();

		/**
		 * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSIGNMENT__OPERATOR = eINSTANCE.getAssignment_Operator();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.CharacterRangeImpl <em>Character Range</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.CharacterRangeImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getCharacterRange()
		 * @generated
		 */
		EClass CHARACTER_RANGE = eINSTANCE.getCharacterRange();

		/**
		 * The meta object literal for the '<em><b>Left</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHARACTER_RANGE__LEFT = eINSTANCE.getCharacterRange_Left();

		/**
		 * The meta object literal for the '<em><b>Right</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHARACTER_RANGE__RIGHT = eINSTANCE.getCharacterRange_Right();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.ConjunctionImpl <em>Conjunction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.ConjunctionImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getConjunction()
		 * @generated
		 */
		EClass CONJUNCTION = eINSTANCE.getConjunction();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONJUNCTION__ELEMENTS = eINSTANCE.getConjunction_Elements();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.DisjunctionImpl <em>Disjunction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.DisjunctionImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getDisjunction()
		 * @generated
		 */
		EClass DISJUNCTION = eINSTANCE.getDisjunction();

		/**
		 * The meta object literal for the '<em><b>Conjunctions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DISJUNCTION__CONJUNCTIONS = eINSTANCE.getDisjunction_Conjunctions();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.EOFImpl <em>EOF</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.EOFImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getEOF()
		 * @generated
		 */
		EClass EOF = eINSTANCE.getEOF();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.EpsilonImpl <em>Epsilon</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.EpsilonImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getEpsilon()
		 * @generated
		 */
		EClass EPSILON = eINSTANCE.getEpsilon();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.GrammarImpl <em>Grammar</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.GrammarImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getGrammar()
		 * @generated
		 */
		EClass GRAMMAR = eINSTANCE.getGrammar();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRAMMAR__NAME = eINSTANCE.getGrammar_Name();

		/**
		 * The meta object literal for the '<em><b>Syntax</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAMMAR__SYNTAX = eINSTANCE.getGrammar_Syntax();

		/**
		 * The meta object literal for the '<em><b>Goals</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAMMAR__GOALS = eINSTANCE.getGrammar_Goals();

		/**
		 * The meta object literal for the '<em><b>Rules</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAMMAR__RULES = eINSTANCE.getGrammar_Rules();

		/**
		 * The meta object literal for the '<em><b>Debug</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GRAMMAR__DEBUG = eINSTANCE.getGrammar_Debug();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.KeywordImpl <em>Keyword</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.KeywordImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getKeyword()
		 * @generated
		 */
		EClass KEYWORD = eINSTANCE.getKeyword();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute KEYWORD__VALUE = eINSTANCE.getKeyword_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.KeywordAssignmentImpl <em>Keyword Assignment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.KeywordAssignmentImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getKeywordAssignment()
		 * @generated
		 */
		EClass KEYWORD_ASSIGNMENT = eINSTANCE.getKeywordAssignment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.LexerGrammarImpl <em>Lexer Grammar</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.LexerGrammarImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getLexerGrammar()
		 * @generated
		 */
		EClass LEXER_GRAMMAR = eINSTANCE.getLexerGrammar();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.NegatedTokenImpl <em>Negated Token</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.NegatedTokenImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getNegatedToken()
		 * @generated
		 */
		EClass NEGATED_TOKEN = eINSTANCE.getNegatedToken();

		/**
		 * The meta object literal for the '<em><b>Terminal</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NEGATED_TOKEN__TERMINAL = eINSTANCE.getNegatedToken_Terminal();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.ParserGrammarImpl <em>Parser Grammar</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.ParserGrammarImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getParserGrammar()
		 * @generated
		 */
		EClass PARSER_GRAMMAR = eINSTANCE.getParserGrammar();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.ParserRuleImpl <em>Parser Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.ParserRuleImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getParserRule()
		 * @generated
		 */
		EClass PARSER_RULE = eINSTANCE.getParserRule();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.RuleCallImpl <em>Rule Call</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.RuleCallImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getRuleCall()
		 * @generated
		 */
		EClass RULE_CALL = eINSTANCE.getRuleCall();

		/**
		 * The meta object literal for the '<em><b>Referred Rule</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE_CALL__REFERRED_RULE = eINSTANCE.getRuleCall_ReferredRule();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE_CALL__NAME = eINSTANCE.getRuleCall_Name();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.RuleCallAssignmentImpl <em>Rule Call Assignment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.RuleCallAssignmentImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getRuleCallAssignment()
		 * @generated
		 */
		EClass RULE_CALL_ASSIGNMENT = eINSTANCE.getRuleCallAssignment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.SyntaxImpl <em>Syntax</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.SyntaxImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getSyntax()
		 * @generated
		 */
		EClass SYNTAX = eINSTANCE.getSyntax();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYNTAX__NAME = eINSTANCE.getSyntax_Name();

		/**
		 * The meta object literal for the '<em><b>Grammars</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYNTAX__GRAMMARS = eINSTANCE.getSyntax_Grammars();

		/**
		 * The meta object literal for the '<em><b>Debug</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SYNTAX__DEBUG = eINSTANCE.getSyntax_Debug();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.TerminalRuleImpl <em>Terminal Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.TerminalRuleImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getTerminalRule()
		 * @generated
		 */
		EClass TERMINAL_RULE = eINSTANCE.getTerminalRule();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.TypedRuleImpl <em>Typed Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.TypedRuleImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getTypedRule()
		 * @generated
		 */
		EClass TYPED_RULE = eINSTANCE.getTypedRule();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPED_RULE__TYPE = eINSTANCE.getTypedRule_Type();

		/**
		 * The meta object literal for the '<em><b>Subrules</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPED_RULE__SUBRULES = eINSTANCE.getTypedRule_Subrules();

		/**
		 * The meta object literal for the '<em><b>Grammar</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPED_RULE__GRAMMAR = eINSTANCE.getTypedRule_Grammar();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.UntilTokenImpl <em>Until Token</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.UntilTokenImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getUntilToken()
		 * @generated
		 */
		EClass UNTIL_TOKEN = eINSTANCE.getUntilToken();

		/**
		 * The meta object literal for the '<em><b>Terminal</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNTIL_TOKEN__TERMINAL = eINSTANCE.getUntilToken_Terminal();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.UntypedRuleImpl <em>Untyped Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.UntypedRuleImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getUntypedRule()
		 * @generated
		 */
		EClass UNTYPED_RULE = eINSTANCE.getUntypedRule();

		/**
		 * The meta object literal for the '<em><b>Typed Rule</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNTYPED_RULE__TYPED_RULE = eINSTANCE.getUntypedRule_TypedRule();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.WildcardImpl <em>Wildcard</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.WildcardImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.XBNFPackageImpl#getWildcard()
		 * @generated
		 */
		EClass WILDCARD = eINSTANCE.getWildcard();

	}

} //XBNFPackage
