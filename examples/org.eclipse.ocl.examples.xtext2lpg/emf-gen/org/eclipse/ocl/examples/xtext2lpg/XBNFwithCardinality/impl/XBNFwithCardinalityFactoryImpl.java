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
package org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class XBNFwithCardinalityFactoryImpl extends EFactoryImpl implements XBNFwithCardinalityFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static XBNFwithCardinalityFactory init() {
		try {
			XBNFwithCardinalityFactory theXBNFwithCardinalityFactory = (XBNFwithCardinalityFactory)EPackage.Registry.INSTANCE.getEFactory(XBNFwithCardinalityPackage.eNS_URI);
			if (theXBNFwithCardinalityFactory != null) {
				return theXBNFwithCardinalityFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new XBNFwithCardinalityFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XBNFwithCardinalityFactoryImpl() {
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
			case XBNFwithCardinalityPackage.ALTERNATIVES: return createAlternatives();
			case XBNFwithCardinalityPackage.ONE_OR_MORE: return createOneOrMore();
			case XBNFwithCardinalityPackage.SUCCESSION: return createSuccession();
			case XBNFwithCardinalityPackage.ZERO_OR_MORE: return createZeroOrMore();
			case XBNFwithCardinalityPackage.ZERO_OR_ONE: return createZeroOrOne();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Alternatives createAlternatives() {
		AlternativesImpl alternatives = new AlternativesImpl();
		return alternatives;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OneOrMore createOneOrMore() {
		OneOrMoreImpl oneOrMore = new OneOrMoreImpl();
		return oneOrMore;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Succession createSuccession() {
		SuccessionImpl succession = new SuccessionImpl();
		return succession;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ZeroOrMore createZeroOrMore() {
		ZeroOrMoreImpl zeroOrMore = new ZeroOrMoreImpl();
		return zeroOrMore;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ZeroOrOne createZeroOrOne() {
		ZeroOrOneImpl zeroOrOne = new ZeroOrOneImpl();
		return zeroOrOne;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XBNFwithCardinalityPackage getXBNFwithCardinalityPackage() {
		return (XBNFwithCardinalityPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static XBNFwithCardinalityPackage getPackage() {
		return XBNFwithCardinalityPackage.eINSTANCE;
	}

} //XBNFwithCardinalityFactoryImpl
