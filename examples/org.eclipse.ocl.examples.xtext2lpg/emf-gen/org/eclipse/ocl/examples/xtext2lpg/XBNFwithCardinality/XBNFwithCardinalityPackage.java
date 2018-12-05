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
package org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage;

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
 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.XBNFwithCardinalityFactory
 * @model kind="package"
 * @generated
 */
public interface XBNFwithCardinalityPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "XBNFwithCardinality";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/ocl/XBNFwithCardinality";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "xbnfwc";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	XBNFwithCardinalityPackage eINSTANCE = org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.XBNFwithCardinalityPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.CompoundElementImpl <em>Compound Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.CompoundElementImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.XBNFwithCardinalityPackageImpl#getCompoundElement()
	 * @generated
	 */
	int COMPOUND_ELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOUND_ELEMENT__DEBUG = XBNFPackage.ABSTRACT_ELEMENT__DEBUG;

	/**
	 * The feature id for the '<em><b>Parent Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOUND_ELEMENT__PARENT_RULE = XBNFPackage.ABSTRACT_ELEMENT__PARENT_RULE;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOUND_ELEMENT__ELEMENTS = XBNFPackage.ABSTRACT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Compound Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOUND_ELEMENT_FEATURE_COUNT = XBNFPackage.ABSTRACT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Compound Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOUND_ELEMENT_OPERATION_COUNT = XBNFPackage.ABSTRACT_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.AlternativesImpl <em>Alternatives</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.AlternativesImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.XBNFwithCardinalityPackageImpl#getAlternatives()
	 * @generated
	 */
	int ALTERNATIVES = 0;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATIVES__DEBUG = COMPOUND_ELEMENT__DEBUG;

	/**
	 * The feature id for the '<em><b>Parent Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATIVES__PARENT_RULE = COMPOUND_ELEMENT__PARENT_RULE;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATIVES__ELEMENTS = COMPOUND_ELEMENT__ELEMENTS;

	/**
	 * The number of structural features of the '<em>Alternatives</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATIVES_FEATURE_COUNT = COMPOUND_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Alternatives</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATIVES_OPERATION_COUNT = COMPOUND_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.MultiplicityElementImpl <em>Multiplicity Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.MultiplicityElementImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.XBNFwithCardinalityPackageImpl#getMultiplicityElement()
	 * @generated
	 */
	int MULTIPLICITY_ELEMENT = 2;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICITY_ELEMENT__DEBUG = XBNFPackage.ABSTRACT_ELEMENT__DEBUG;

	/**
	 * The feature id for the '<em><b>Parent Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICITY_ELEMENT__PARENT_RULE = XBNFPackage.ABSTRACT_ELEMENT__PARENT_RULE;

	/**
	 * The feature id for the '<em><b>Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICITY_ELEMENT__ELEMENT = XBNFPackage.ABSTRACT_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Multiplicity Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICITY_ELEMENT_FEATURE_COUNT = XBNFPackage.ABSTRACT_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Multiplicity Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICITY_ELEMENT_OPERATION_COUNT = XBNFPackage.ABSTRACT_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.OneOrMoreImpl <em>One Or More</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.OneOrMoreImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.XBNFwithCardinalityPackageImpl#getOneOrMore()
	 * @generated
	 */
	int ONE_OR_MORE = 3;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONE_OR_MORE__DEBUG = MULTIPLICITY_ELEMENT__DEBUG;

	/**
	 * The feature id for the '<em><b>Parent Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONE_OR_MORE__PARENT_RULE = MULTIPLICITY_ELEMENT__PARENT_RULE;

	/**
	 * The feature id for the '<em><b>Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONE_OR_MORE__ELEMENT = MULTIPLICITY_ELEMENT__ELEMENT;

	/**
	 * The number of structural features of the '<em>One Or More</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONE_OR_MORE_FEATURE_COUNT = MULTIPLICITY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>One Or More</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ONE_OR_MORE_OPERATION_COUNT = MULTIPLICITY_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.SuccessionImpl <em>Succession</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.SuccessionImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.XBNFwithCardinalityPackageImpl#getSuccession()
	 * @generated
	 */
	int SUCCESSION = 4;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUCCESSION__DEBUG = COMPOUND_ELEMENT__DEBUG;

	/**
	 * The feature id for the '<em><b>Parent Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUCCESSION__PARENT_RULE = COMPOUND_ELEMENT__PARENT_RULE;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUCCESSION__ELEMENTS = COMPOUND_ELEMENT__ELEMENTS;

	/**
	 * The number of structural features of the '<em>Succession</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUCCESSION_FEATURE_COUNT = COMPOUND_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Succession</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUCCESSION_OPERATION_COUNT = COMPOUND_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.ZeroOrMoreImpl <em>Zero Or More</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.ZeroOrMoreImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.XBNFwithCardinalityPackageImpl#getZeroOrMore()
	 * @generated
	 */
	int ZERO_OR_MORE = 5;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ZERO_OR_MORE__DEBUG = MULTIPLICITY_ELEMENT__DEBUG;

	/**
	 * The feature id for the '<em><b>Parent Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ZERO_OR_MORE__PARENT_RULE = MULTIPLICITY_ELEMENT__PARENT_RULE;

	/**
	 * The feature id for the '<em><b>Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ZERO_OR_MORE__ELEMENT = MULTIPLICITY_ELEMENT__ELEMENT;

	/**
	 * The number of structural features of the '<em>Zero Or More</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ZERO_OR_MORE_FEATURE_COUNT = MULTIPLICITY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Zero Or More</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ZERO_OR_MORE_OPERATION_COUNT = MULTIPLICITY_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.ZeroOrOneImpl <em>Zero Or One</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.ZeroOrOneImpl
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.XBNFwithCardinalityPackageImpl#getZeroOrOne()
	 * @generated
	 */
	int ZERO_OR_ONE = 6;

	/**
	 * The feature id for the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ZERO_OR_ONE__DEBUG = MULTIPLICITY_ELEMENT__DEBUG;

	/**
	 * The feature id for the '<em><b>Parent Rule</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ZERO_OR_ONE__PARENT_RULE = MULTIPLICITY_ELEMENT__PARENT_RULE;

	/**
	 * The feature id for the '<em><b>Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ZERO_OR_ONE__ELEMENT = MULTIPLICITY_ELEMENT__ELEMENT;

	/**
	 * The number of structural features of the '<em>Zero Or One</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ZERO_OR_ONE_FEATURE_COUNT = MULTIPLICITY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Zero Or One</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ZERO_OR_ONE_OPERATION_COUNT = MULTIPLICITY_ELEMENT_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.Alternatives <em>Alternatives</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Alternatives</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.Alternatives
	 * @generated
	 */
	EClass getAlternatives();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.CompoundElement <em>Compound Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Compound Element</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.CompoundElement
	 * @generated
	 */
	EClass getCompoundElement();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.CompoundElement#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.CompoundElement#getElements()
	 * @see #getCompoundElement()
	 * @generated
	 */
	EReference getCompoundElement_Elements();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.MultiplicityElement <em>Multiplicity Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Multiplicity Element</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.MultiplicityElement
	 * @generated
	 */
	EClass getMultiplicityElement();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.MultiplicityElement#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Element</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.MultiplicityElement#getElement()
	 * @see #getMultiplicityElement()
	 * @generated
	 */
	EReference getMultiplicityElement_Element();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.OneOrMore <em>One Or More</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>One Or More</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.OneOrMore
	 * @generated
	 */
	EClass getOneOrMore();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.Succession <em>Succession</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Succession</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.Succession
	 * @generated
	 */
	EClass getSuccession();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.ZeroOrMore <em>Zero Or More</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Zero Or More</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.ZeroOrMore
	 * @generated
	 */
	EClass getZeroOrMore();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.ZeroOrOne <em>Zero Or One</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Zero Or One</em>'.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.ZeroOrOne
	 * @generated
	 */
	EClass getZeroOrOne();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	XBNFwithCardinalityFactory getXBNFwithCardinalityFactory();

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
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.AlternativesImpl <em>Alternatives</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.AlternativesImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.XBNFwithCardinalityPackageImpl#getAlternatives()
		 * @generated
		 */
		EClass ALTERNATIVES = eINSTANCE.getAlternatives();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.CompoundElementImpl <em>Compound Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.CompoundElementImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.XBNFwithCardinalityPackageImpl#getCompoundElement()
		 * @generated
		 */
		EClass COMPOUND_ELEMENT = eINSTANCE.getCompoundElement();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOUND_ELEMENT__ELEMENTS = eINSTANCE.getCompoundElement_Elements();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.MultiplicityElementImpl <em>Multiplicity Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.MultiplicityElementImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.XBNFwithCardinalityPackageImpl#getMultiplicityElement()
		 * @generated
		 */
		EClass MULTIPLICITY_ELEMENT = eINSTANCE.getMultiplicityElement();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MULTIPLICITY_ELEMENT__ELEMENT = eINSTANCE.getMultiplicityElement_Element();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.OneOrMoreImpl <em>One Or More</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.OneOrMoreImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.XBNFwithCardinalityPackageImpl#getOneOrMore()
		 * @generated
		 */
		EClass ONE_OR_MORE = eINSTANCE.getOneOrMore();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.SuccessionImpl <em>Succession</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.SuccessionImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.XBNFwithCardinalityPackageImpl#getSuccession()
		 * @generated
		 */
		EClass SUCCESSION = eINSTANCE.getSuccession();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.ZeroOrMoreImpl <em>Zero Or More</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.ZeroOrMoreImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.XBNFwithCardinalityPackageImpl#getZeroOrMore()
		 * @generated
		 */
		EClass ZERO_OR_MORE = eINSTANCE.getZeroOrMore();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.ZeroOrOneImpl <em>Zero Or One</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.ZeroOrOneImpl
		 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.XBNFwithCardinalityPackageImpl#getZeroOrOne()
		 * @generated
		 */
		EClass ZERO_OR_ONE = eINSTANCE.getZeroOrOne();

	}

} //XBNFwithCardinalityPackage
