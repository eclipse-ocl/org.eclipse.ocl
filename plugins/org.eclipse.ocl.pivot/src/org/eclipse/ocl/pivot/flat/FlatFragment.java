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
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * An InheritanceFragment identifies the capabilities introduced by a particular inheritance.
 * @since 7.0
 */
public abstract class FlatFragment
{
	protected final @NonNull FlatClass derivedInheritance;
	protected final @NonNull FlatClass baseInheritance;

	public FlatFragment(@NonNull FlatClass derivedInheritance, @NonNull FlatClass baseInheritance) {
		this.derivedInheritance = derivedInheritance;
		this.baseInheritance = baseInheritance;
	}

	/**
	 * Return the unoverloaded fragment, which is getBaseInheritance().getSelfFragment().
	 */
	public final @NonNull FlatFragment getBaseFragment() {
		return baseInheritance.getSelfFragment();
	}

	/**
	 * Return the inheritance that introduces the operations and properties in this fragment.
	 */
	public final @NonNull FlatClass getBaseInheritance() {
		return baseInheritance;
	}

	/**
	 * Return the inheritance that overloads the operations and properties in this fragment.
	 */
	public final @NonNull FlatClass getDerivedInheritance() {
		return derivedInheritance;
	}

	/**
	 * Return the actualOperation that has the same signature as apparentOperation.
	 */
	public @NonNull Operation getActualOperation(@NonNull Operation apparentOperation) {
		Operation localOperation = getLocalOperation(apparentOperation);
		if (localOperation == null) {
			if (derivedInheritance == baseInheritance) {
				localOperation = apparentOperation;
			}
		}
		if (localOperation == null) {				// Non-trivial, search up the inheritance tree for an inherited operation
			Operation bestOverload = null;
			FlatClass bestInheritance = null;
			int bestDepth = -1;
			int minDepth = baseInheritance.getDepth();
			for (int depth = derivedInheritance.getDepth()-1; depth >= minDepth; depth--) {
				Iterable<FlatFragment> derivedSuperFragments = derivedInheritance.getSuperFragments(depth);
				for (FlatFragment derivedSuperFragment : derivedSuperFragments) {
					FlatClass superInheritance = derivedSuperFragment.getBaseInheritance();
					FlatFragment superFragment = superInheritance.getFragment(baseInheritance);
					if (superFragment != null) {
						Operation overload = superFragment.getLocalOperation(apparentOperation);
						if (overload != null) {
							if (bestInheritance == null) {				// First candidate
								bestDepth = depth;
								bestInheritance = superInheritance;
								bestOverload = overload;
							}
							else if (depth == bestDepth) {				// Sibling candidate
								bestOverload = null;
								depth = -1;
								break;
							}
							else if (!bestInheritance.isSubFlatClassOf(superInheritance)) {	// Non-occluded child candidate
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
			else if (bestInheritance == null) {
				localOperation = apparentOperation;		// FIXME Missing operation
			}
			else {
				throw new InvalidValueException(PivotMessages.AmbiguousOperation, apparentOperation, derivedInheritance);
			}
		}
		//		if (localOperation == null) {
		//			localOperation = INVALID;
		//		}
		//		if (localOperation == null) {
		//			localOperation = apparentOperation;
		//		}
		return localOperation;
	}

	/**
	 * Return the implementation of the actualOperation within this fragment that has the same signature as apparentOperation.
	 * If there is no local overload, returns an inherited operation if unambiguous or OclAnyUnsupportedOperation.AMBIGUOUS
	 * if ambiguous.
	 */
	public abstract @NonNull LibraryFeature getImplementation(@NonNull Operation apparentOperation);

	/**
	 * Return the operation within this fragment that has the same signature as apparentOperation. Returns null if none.
	 */
	public abstract @Nullable Operation getLocalOperation(@NonNull Operation apparentOperation);

	/**
	 * Return the operations within this fragment in operation index order.
	 */
	public abstract @NonNull Iterable<@NonNull ? extends Operation> getLocalOperations();

	/**
	 * Return the properties within this fragment in property index order.
	 */
	public abstract @NonNull Iterable<@NonNull ? extends Property> getLocalProperties();
	/**
	 * Return true if anOperation overloads an existing operation.
	 *
	protected boolean isOverload(DomainOperation anOperation) {
		int depth = derivedInheritance.getDepth();
		for (int i = 0; i <= depth-1; i++) {
			for (DomainInheritance superInheritance : derivedInheritance.getSuperInheritances(depth)) {
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
	//						// FIXME
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
		return derivedInheritance.toString() + "__" + baseInheritance.toString(); //$NON-NLS-1$
	}
}