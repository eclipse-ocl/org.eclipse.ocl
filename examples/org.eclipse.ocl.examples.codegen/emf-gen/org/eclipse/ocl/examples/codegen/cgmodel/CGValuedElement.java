/*******************************************************************************
 * Copyright (c) 2013, 2018 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cgmodel;

import java.util.List;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;




/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CG Valued Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement#getDependsOn <em>Depends On</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement#getOwns <em>Owns</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGValuedElement()
 * @generated
 */
public interface CGValuedElement extends CGTypedElement {

	/**
	 * Returns the value of the '<em><b>Depends On</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The elements that must be declared before this element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Depends On</em>' reference list.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGValuedElement_DependsOn()
	 * @generated
	 */
	List<CGValuedElement> getDependsOn();

	/**
	 * Returns the value of the '<em><b>Owns</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owns</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owns</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGValuedElement_Owns()
	 * @generated
	 */
	List<CGValuedElement> getOwns();

	/**
	 * Return a non-null EClassifier if this value has a known EClassifier to define its instance class.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Box$15
	@Nullable EClassifier getEcoreClassifier();

	/**
	 * Return a non-null invalid value if this value is invalid.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Inv$10
	@Nullable CGInvalid getInvalidValue();

	/**
	 * Return the CGValuedElement that provides the name of a declaration from which the value of this CGValuedElement may be obtained.
	 * Fundamental elements such as constants and operation calls provide the named value themselves.
	 * More complex elements such as VariableExp and ThrowExp may delegate.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Val$7
	@NonNull CGValuedElement getNamedValue();

	/**
	 * Return the CGValuedElement to which the value of this CGValuedElement delegates.
	 * Fundamental elements such as constants and operation calls provide the named value themselves.
	 * More complex elements such as VariableExp and ThrowExp may delegate.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Val$8
	@NonNull CGValuedElement getReferredValue();

	/**
	 * Return the CGValuedElement which is the source of the information value of this element.
	 * Note that the source value may be differently guarded, caught/thrown and boxed/unboxed to this value.
	 * Returns this if no delegation occurs.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Val$8
	@NonNull CGValuedElement getSourceValue();

	/**
	 * Return a simpler CGValuedElement that is fully equivalent to this element.
	 * May return CGInvalid if this element is invalid.
	 * May return the internal value of a CollectionPart or TuplePart.
	 * May follow a delegation when the delegation has no semantic significance.
	 * Returns this if no simplification occurs.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Ths$5
	@NonNull CGValuedElement getThisValue();

	/**
	 * Return the CGValuedElement that provides the narrowest type declaration from which the type of this CGValuedElement may be obtained.
	 * Fundamental elements such as constants and operations call provide their own narrow type.
	 * More complex elements such as CatchExp have a wide type that includes invalid and so delegate to identify the narrow type.
	 * More complex elements such as GuardExp/ThrowExp propagate an unchanged value with a narrower type than their source.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Val$9
	@NonNull CGValuedElement getTypedValue();

	/**
	 * Return the declared name value of this element. The text is valid for use in the target language and
	 * unique within the context in which this element is declared.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Val$10
	@Nullable String getValueName();

	/**
	 * Return true if this value is not null, possibly with the aid of an assertion.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Nul$12
	boolean isAssertedNonNull();

	/**
	 * Return true if this value is a boxed value.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Box$9
	boolean isBoxed();

	/**
	 * Returns true if any exception associated with this value has been caught and consequently the value may be an InvalidValueException.
	 * Returns true if any exception has been thrown.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Ct$4
	boolean isCaught();

	/**
	 * Return true if this value can be shared as part of a Common Subexpression.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Com$5
	boolean isCommonable();

	/**
	 * Return true if this value is a local constant (dependent of the user type system).
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Con$9
	boolean isConstant();

	/**
	 * Return true if this value is an Ecore value.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Box$11
	boolean isEcore();

	/**
	 * Returns true/false if this value can be determined to have deep value equivalence/inequivalence to thatValue, null if no determination can be made.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Eq$15
	@Nullable Boolean isEquivalentTo(@NonNull CGValuedElement thatValue);

	/**
	 * Provided that thatValue does not delegate its equivalence computation, return true/false if this value can be determined to have deep value equivalence/inequivalence to thatValue, null if no determination can be made.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Eq$16
	@Nullable Boolean isEquivalentToInternal(@NonNull CGValuedElement thatValue);

	/**
	 * Return true if this value is false.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Log$8
	boolean isFalse();

	/**
	 * Return true if this value is a global constant (independent of the user type system).
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Glo$7
	boolean isGlobal();

	/**
	 * Return true if this value is inlined and so has no local or global declaration.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Inl$7
	boolean isInlined();

	/**
	 * Return true if this value is false.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Inv$11
	boolean isInvalid();

	/**
	 * Return true if this value is false.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Inv$12
	boolean isNonInvalid();

	/**
	 * Return true if this value is not null.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Nul$11
	boolean isNonNull();

	/**
	 * Return true if this value is null.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Nul$12
	boolean isNull();

	/**
	 * Return true if this value can be inlined as an expression term.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Set$3
	boolean isSettable();

	/**
	 * Return true if this value is true.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Log$9
	boolean isTrue();

	/**
	 * Return true if this value is an unboxed value.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Box$10
	boolean isUnboxed();

	/**
	 * Return true if this value does not have to be shared as part of a Common Subexpression.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Com$6
	boolean isUncommonable();

	/**
	 * Set the caught status.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Ct$5
	void setCaught(boolean isCaught);

	/**
	 * Set the name of the value.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$Val$11
	void setValueName(@NonNull String valueName);
} // CGValuedElement
