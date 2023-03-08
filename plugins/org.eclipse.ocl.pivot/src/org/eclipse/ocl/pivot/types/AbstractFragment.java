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
package org.eclipse.ocl.pivot.types;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.InheritanceFragment;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.values.InvalidValueException;

public abstract class AbstractFragment implements InheritanceFragment
{
	protected final @NonNull FlatClass derivedFlatClass;
	protected final @NonNull FlatClass baseFlatClass;

	public AbstractFragment(@NonNull FlatClass derivedFlatClass, @NonNull FlatClass baseFlatClass) {
		this.derivedFlatClass = derivedFlatClass;
		this.baseFlatClass = baseFlatClass;
	}

	/**
	 * Return the actualOperation that has the same signature as apparentOperation.
	 */
	@Override
	public @NonNull Operation getActualOperation(@NonNull Operation apparentOperation) {
		Operation localOperation = getLocalOperation(apparentOperation);
		if (localOperation == null) {
			if (derivedFlatClass == baseFlatClass) {
				localOperation = apparentOperation;
			}
		}
		if (localOperation == null) {				// Non-trivial, search up the inheritance tree for an inherited operation
			Operation bestOverload = baseFlatClass.getBestOverload(derivedFlatClass, apparentOperation);
			if (bestOverload != null) {
				localOperation = bestOverload;
			}
			else {
				throw new InvalidValueException(PivotMessages.AmbiguousOperation, apparentOperation, derivedFlatClass);
			}
		}
		return localOperation;
	}

	@Override
	public final @NonNull InheritanceFragment getBaseFragment() {
		return baseFlatClass.getSelfFragment();
	}

	@Override
	public final @NonNull FlatClass getBaseFlatClass() {
		return baseFlatClass;
	}

	@Override
	public final @NonNull FlatClass getDerivedFlatClass() {
		return derivedFlatClass;
	}

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
//				ExecutorTypeArgument[] parameterTypeArguments = operation.getParameterType();
				if (parameterTypeArguments.size() == argumentTypes.length) {
					for (int i = 0; i < parameterTypeArguments.size(); i++) {
						DomainType argumentType = argumentTypes[i];
						DomainType parameterTypeArgument = parameterTypeArguments.get(i);
//						if (parameterTypeArgument instanceof ExecutorType) {
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

//						}
//						else {
//							// FIXME
//						}
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
	public @NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> getSuperClasses() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull String toString() {
		return derivedFlatClass.toString() + "__" + baseFlatClass.toString(); //$NON-NLS-1$
	}
}