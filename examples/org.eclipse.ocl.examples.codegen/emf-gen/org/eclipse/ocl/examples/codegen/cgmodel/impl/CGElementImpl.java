/*******************************************************************************
 * Copyright (c) 2013, 2019 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cgmodel.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CG2StringVisitor;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.util.CGModelVisitor;
import org.eclipse.ocl.examples.codegen.cse.AbstractPlace;
import org.eclipse.ocl.examples.codegen.cse.GlobalPlace;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CG Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class CGElementImpl extends MinimalEObjectImpl.Container implements CGElement {
	/**
	 * The number of structural features of the '<em>CG Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CG_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CGElementImpl() {
		super();
	}

	/**
	 * Overridden to detect child stealing whereby a previous parent is inadvertently displaced by another one leading to obscure downstream errors.
	 * If a rewrite is genuinely intended, precede the call by a container reset. See {@link PivotUtilInternal#resetContainer(EObject)}.
	 */
	@Override
	protected void eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID) {
		if (newContainer != null) {
			EObject oldContainer = eInternalContainer();
			assert (oldContainer == null)					// The expected use case
			/*|| (newContainer == oldContainer)*/			// OK, a gratuitous redundant use case, but hides a problem in the caller
			/*|| oldContainer.eIsProxy()*/					// Is it OK? probably hides a problem in the caller
			/*|| (oldContainer.eResource() == null)*/;		// Not OK, working in an orphan tree is another problem
		}
		super.eBasicSetContainer(newContainer, newContainerFeatureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGModelPackage.Literals.CG_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString() {
		return CG2StringVisitor.toString(this);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull CGModelVisitor<R> visitor) {
		return visitor.visitCGElement(this);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @NonNull Iterable<@NonNull ? extends CGElement> getChildren() {
		@SuppressWarnings({"null", "unchecked"}) @NonNull Iterable<@NonNull ? extends CGElement> eContents = (Iterable<@NonNull ? extends CGElement>) eContents();
		return eContents;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable CGElement getParent() {
		return (CGElement)eContainer();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable AbstractPlace getPlace(@NonNull Map<@Nullable CGElement, @NonNull AbstractPlace> element2place) {
		return GlobalPlace.createGlobalPlace(element2place, this);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isContext() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean rewriteAs(@NonNull CGValuedElement oldValue, @NonNull CGValuedElement newValue) {
		throw new UnsupportedOperationException(getClass().getName() + ".rewriteAs()");
	}

} //CGElementImpl
