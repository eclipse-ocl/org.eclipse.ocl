/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.flat;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;

/**
 * A FlatFragment provides the description of the properties and operations defined by some class when accessed by the same
 * or another class. The descriptions are normally built by direct static construction from auto-generated code, with instnaces defined
 * in isolation during construction then cross-references defined later by calls to init().
 */
public /*final*/ class FlatFragment
{
	/**
	 * The derived FlatClass to which this FlatFragment contributes features.
	 */
	public final @NonNull FlatClass derivedFlatClass;

	/**
	 * The base FlatClass from which this FlatFragment contributes features.
	 */
	public final @NonNull FlatClass baseFlatClass;

	/**
	 * The operations defined by the baseFlatClass of this fragment. Initially null, non null once initialized.
	 * FIXME legacy static initialization has some super operations too.
	 */
	private @NonNull Operation @Nullable [] operations = null;

	/**
	 * The properties defined by the baseFlatClass of this fragment. Initially null, non null once initialized.
	 * FIXME legacy static initialization has some super properties too.
	 */
	private @NonNull Property @Nullable [] properties = null;

	public FlatFragment(@NonNull FlatClass derivedFlatClass, @NonNull FlatClass baseFlatClass) {
		this.derivedFlatClass = derivedFlatClass;
		this.baseFlatClass = baseFlatClass;
	}

	public @NonNull Operation @Nullable [] basicGetOperations() {
		return operations;
	}

	public @NonNull Property @Nullable [] basicGetProperties() {
		return properties;
	}

	/**
	 * Return the unoverloaded fragment, which is getBaseInheritance().getSelfFragment().
	 */
	public final @NonNull FlatFragment getBaseFragment() {
		return baseFlatClass.getSelfFragment();
	}

	/**
	 * Return the FlatClass that introduces the operations and properties in this fragment.
	 */
	public final @NonNull FlatClass getBaseFlatClass() {
		return baseFlatClass;
	}

	/**
	 * Return the FlatClass that overloads the operations and properties in this fragment.
	 */
	public final @NonNull FlatClass getDerivedFlatClass() {
		return derivedFlatClass;
	}

	public @NonNull Operation @NonNull [] getOperations() {
		@NonNull Operation [] operations2 = this.operations;
		if (operations2 == null) {
			this.operations = operations2 = ((AbstractFlatClass)baseFlatClass).computeDirectOperations();
		}
		return operations2;
	}

	public @NonNull Property @NonNull [] getProperties() {
		@NonNull Property [] properties2 = this.properties;
		if (properties2 == null) {
			this.properties = properties2 = ((AbstractFlatClass)baseFlatClass).computeDirectProperties();
		}
		return properties2;
	}

	public void initOperations(@NonNull Operation @NonNull [] operations) {
		assert this.operations == null;
		this.operations = operations;
	}

	public void initProperties(@NonNull Property @NonNull [] properties) {
		assert this.properties == null;
		this.properties = properties;
	}

	@Override
	public @NonNull String toString() {
		return derivedFlatClass.toString() + "__" + baseFlatClass.toString(); //$NON-NLS-1$
	}
}