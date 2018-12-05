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

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.util.CGModelVisitor;
import org.eclipse.ocl.examples.codegen.cse.AbstractPlace;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CG Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * CGElement is the abstract class from which all classes contribution to a CG tree derive.
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage#getCGElement()
 * @generated
 */
public interface CGElement extends EObject {

	/**
	 * Accept a visit from a visitor and return the result of a call to the derived type-specific visitXXX in the visitor.
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build/templates/model/Class/insert.javajetinc
	public <R> R accept(@NonNull CGModelVisitor<R> visitor);

	/**
	 * Return the child CGElements.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGElementModelSpec$1
	@NonNull Iterable<@NonNull ? extends CGElement> getChildren();

	/**
	 * Return the parent node in a CG tree, null at the root.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGElementModelSpec$2
	@Nullable CGElement getParent();

	/**
	 * Returns the place for this element, updating and reusing element2place as required.
	 * All parent elements have entries in element2place.
	 * No child elements have entries in element2place.
	 * The global place is accessible as the null element.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$2
	@Nullable AbstractPlace getPlace(@NonNull Map<@Nullable CGElement, @NonNull AbstractPlace> element2place);

	/**
	 * Returns true if this node may have its own symbol name context.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$10
	boolean isContext();

	/**
	 * Rewrite the reference to oldValue by newValue.
	 *
	 * @generated
	 */
	// Generated from org.eclipse.ocl.examples.build.modelspecs.CGValuedElementModelSpec$22
	boolean rewriteAs(@NonNull CGValuedElement oldValue, @NonNull CGValuedElement newValue);
} // CGElement
