/**
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.examples.xtext.idioms;

import java.util.regex.Pattern;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Idiom</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An Idiom defines a formatting policy comprising one or more SubIdioms each of which
 * identifies a grammar term at which formatting is to be applied. Thus a dual SubIdiom,
 * may locate an opening brace in first SubIdiom and a closing brace in a second and impose
 * new-lines as desired. The idiom can be constrained to a particular
 * returned EClass or its derivation, or rules whose name matches a regex.
 *
 * The serialization variant of each grammar rule is analyzed to determine which Idioms
 * configure the formatting of each term in the rule. Each Idiom has one or more SubIdioms
 * whose locator may or may not match a term. Where all SubIdioms match, the corresponding
 * segemnts format the matching term. Idioms can match recursively and sequentially. Partial
 * Idiom matches are ignored.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.Idiom#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.Idiom#getForEPackage <em>For EPackage</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.Idiom#getForEClass <em>For EClass</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.Idiom#getInRuleRegex <em>In Rule Regex</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.Idiom#isMixin <em>Mixin</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.Idiom#getOwnedSubIdioms <em>Owned Sub Idioms</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getIdiom()
 * @model
 * @generated
 */
public interface Idiom
		extends IdiomsElement {

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getIdiom_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.idioms.Idiom#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>For EPackage</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An optional scope restriction on the matching of the string. If specified matches are
	 * only discovered in rules that produce the EClass or a derivation thereof.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>For EPackage</em>' reference.
	 * @see #setForEPackage(EPackage)
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getIdiom_ForEPackage()
	 * @model
	 * @generated
	 */
	EPackage getForEPackage();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.idioms.Idiom#getForEPackage <em>For EPackage</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>For EPackage</em>' reference.
	 * @see #getForEPackage()
	 * @generated
	 */
	void setForEPackage(EPackage value);

	/**
	 * Returns the value of the '<em><b>For EClass</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An optional scope restriction on the matching of the string. If specified matches are
	 * only discovered in rules that produce the EClass or a derivation thereof.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>For EClass</em>' reference.
	 * @see #setForEClass(EClass)
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getIdiom_ForEClass()
	 * @model
	 * @generated
	 */
	EClass getForEClass();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.idioms.Idiom#getForEClass <em>For EClass</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>For EClass</em>' reference.
	 * @see #getForEClass()
	 * @generated
	 */
	void setForEClass(EClass value);

	/**
	 * Returns the value of the '<em><b>In Rule Regex</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Rule Regex</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Rule Regex</em>' attribute.
	 * @see #setInRuleRegex(String)
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getIdiom_InRuleRegex()
	 * @model
	 * @generated
	 */
	String getInRuleRegex();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.idioms.Idiom#getInRuleRegex <em>In Rule Regex</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>In Rule Regex</em>' attribute.
	 * @see #getInRuleRegex()
	 * @generated
	 */
	void setInRuleRegex(String value);

	/**
	 * Returns the value of the '<em><b>Mixin</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mixin</em>' attribute.
	 * @see #setMixin(boolean)
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getIdiom_Mixin()
	 * @model default="false"
	 * @generated
	 */
	boolean isMixin();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.idioms.Idiom#isMixin <em>Mixin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mixin</em>' attribute.
	 * @see #isMixin()
	 * @generated
	 */
	void setMixin(boolean value);

	/**
	 * Returns the value of the '<em><b>Owned Sub Idioms</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.xtext.idioms.SubIdiom}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.examples.xtext.idioms.SubIdiom#getOwningIdiom <em>Owning Idiom</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Sub Idioms</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The SubIdioms, each of which must match for the Idiom to match.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Sub Idioms</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage#getIdiom_OwnedSubIdioms()
	 * @see org.eclipse.ocl.examples.xtext.idioms.SubIdiom#getOwningIdiom
	 * @model opposite="owningIdiom" containment="true"
	 * @generated
	 */
	EList<SubIdiom> getOwnedSubIdioms();

	Pattern getRegexPattern();
} // Idiom
