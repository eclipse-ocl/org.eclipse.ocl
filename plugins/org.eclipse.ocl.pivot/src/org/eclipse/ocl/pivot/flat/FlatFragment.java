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
import org.eclipse.ocl.pivot.flat.EcoreFlatClass.EcoreFlatProperty;

/**
 * A FlatFragment provides the description of the properties and operations defined by some class when accessed by the same
 * or another class. The descriptions are normally built by direct static construction from auto-generated code, with instnaces defined
 * in isolation during construction then cross-references defined later by calls to init().
 * @since 7.0
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

	/**
	 * Return the actualOperation that has the same signature as apparentOperation.
	 *
	@Deprecated
	public @NonNull Operation getActualOperation(@NonNull Operation apparentOperation) {
		Operation localOperation = getLocalOperation(apparentOperation);
		if (localOperation == null) {
			if (derivedFlatClass == baseFlatClass) {
				localOperation = apparentOperation;
			}
		}
		if (localOperation == null) {				// Non-trivial, search up the inheritance tree for an inherited operation
			Operation bestOverload = null;
			FlatClass bestFlatClass = null;
			int bestDepth = -1;
			int minDepth = baseFlatClass.getDepth();
			for (int depth = derivedFlatClass.getDepth()-1; depth >= minDepth; depth--) {
				Iterable<FlatFragment> derivedSuperFragments = derivedFlatClass.getSuperFragments(depth);
				for (FlatFragment derivedSuperFragment : derivedSuperFragments) {
					FlatClass superFlatClass = derivedSuperFragment.getBaseFlatClass();
					FlatFragment superFragment = superFlatClass.getFragment(baseFlatClass);
					if (superFragment != null) {
						Operation overload = superFragment.getLocalOperation(apparentOperation);
						if (overload != null) {
							if (bestFlatClass == null) {				// First candidate
								bestDepth = depth;
								bestFlatClass = superFlatClass;
								bestOverload = overload;
							}
							else if (depth == bestDepth) {				// Sibling candidate
								bestOverload = null;
								depth = -1;
								break;
							}
							else if (!bestFlatClass.isSubFlatClassOf(superFlatClass)) {	// Non-occluded child candidate
								bestOverload = null;
								depth = -1;
								break;
							}
						}
					}
				}
			}
			if (bestOverload != null) {
				localOperation = bestOverload;
			}
			else if (bestFlatClass == null) {
				localOperation = apparentOperation;		// F I X M E Missing operation
			}
			else {
				throw new InvalidValueException(PivotMessages.AmbiguousOperation, apparentOperation, derivedFlatClass);
			}
		}
		//		if (localOperation == null) {
		//			localOperation = INVALID;
		//		}
		//		if (localOperation == null) {
		//			localOperation = apparentOperation;
		//		}
		return localOperation;
	} */

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
			properties2 = ((AbstractFlatClass)baseFlatClass).computeDirectProperties();
			initProperties(properties2);
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
		for (@NonNull Property property : properties) {
			assert (property instanceof EcoreFlatProperty) || (property.eResource() != null);				// XXX regularize EcoreFlatProperty once FlatClass is-a Class etc
		}
	}

	/**
	 * Return true if anOperation overloads an existing operation.
	 *
	protected boolean isOverload(DomainOperation anOperation) {
		int depth = derivedFlatClass.getDepth();
		for (int i = 0; i <= depth-1; i++) {
			for (DomainInheritance superInheritance : derivedFlatClass.getSuperInheritances(depth)) {
				DomainFragment baseFragment = superInheritance.getSelfFragment();
				for (DomainOperation baseOperation : baseFragment.getOperations()) {
					if (isOverload(anOperation, baseOperation)) {
						return true;
					}
				}
			}
		}
		return false;
	} */

	/*	public DomainOperation lookupOperation(DomainStandardLibrary standardLibrary, DomainType staticType, String operationName, DomainType[] argumentTypes) {
		for (DomainOperation operation : getOperations()) {		// FIXME binary search
			if (operation.getName().equals(operationName)) {
				boolean gotIt = true;
				IndexableIterable<? extends DomainType> parameterTypeArguments = operation.getParameterType();
	//			ExecutorTypeArgument[] parameterTypeArguments = operation.getParameterType();
				if (parameterTypeArguments.size() == argumentTypes.length) {
					for (int i = 0; i < parameterTypeArguments.size(); i++) {
						DomainType argumentType = argumentTypes[i];
						DomainType parameterTypeArgument = parameterTypeArguments.get(i);
	//					if (parameterTypeArgument instanceof ExecutorType) {
							DomainType parameterType;
							if (parameterTypeArgument == standardLibrary.getOclSelfType()) {
								parameterType = staticType;
							}
							else {
								parameterType = parameterTypeArgument;
							}
							if (!argumentType.conformsTo(standardLibrary, parameterType)) {
								gotIt = false;
								break;
							}

	//					}
	//					else {
	//						// F I X M E
	//					}
					}
				}
				if (gotIt) {
					return operation;
				}
			}
		}
		return null;
	} */

	@Override
	public @NonNull String toString() {
		return derivedFlatClass.toString() + "__" + baseFlatClass.toString(); //$NON-NLS-1$
	}
}