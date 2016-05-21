/*******************************************************************************
 * Copyright (c) 2011, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library.executor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.InheritanceFragment;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation;
import org.eclipse.ocl.pivot.types.AbstractFragment;

/**
 * A ReflectiveFragment provides the description of the properties and operations defined by some class when accessed by the same
 * or another class. The descriptions are normally built lazily and one name at a time using reflective access to some meta-model.
 */
public abstract class ReflectiveFragment extends AbstractFragment
{
	protected Map<Operation, LibraryFeature> operationMap = null;
	protected Map<Operation, Operation> apparentOperation2actualOperation = null;
	protected Map<Property, LibraryFeature> propertyMap = null;

	public ReflectiveFragment(@NonNull CompleteInheritance derivedInheritance, @NonNull CompleteInheritance baseInheritance) {
		super(derivedInheritance, baseInheritance);
	}

	@Override
	public @NonNull LibraryFeature getImplementation(@NonNull Operation apparentOperation) {
		if (operationMap == null) {
			synchronized (this) {
				if (operationMap == null) {
					operationMap = new HashMap<Operation, LibraryFeature>();		// Optimize to reuse single super map if no local ops
				}
			}
		}
		LibraryFeature libraryFeature = operationMap.get(apparentOperation);
		if (libraryFeature != null) {
			return libraryFeature;
		}
		synchronized (operationMap) {
			libraryFeature = operationMap.get(apparentOperation);
			if (libraryFeature != null) {
				return libraryFeature;
			}
			Operation localOperation = getLocalOperation(apparentOperation);
			if (localOperation == null) {
				if (derivedInheritance == baseInheritance) {
					localOperation = apparentOperation;
				}
			}
			if (localOperation != null) {				// Trivial case, there is a local operation
				libraryFeature = PivotUtilInternal.getImplementation(localOperation);
			}
			else {										// Non-trivial, search up the inheritance tree for an inherited operation
				Operation bestOverload = null;
				CompleteInheritance bestInheritance = null;
				int bestDepth = -1;
				int minDepth = baseInheritance.getDepth();
				for (int depth = derivedInheritance.getDepth()-1; depth >= minDepth; depth--) {
					Iterable<InheritanceFragment> derivedSuperFragments = derivedInheritance.getSuperFragments(depth);
					for (InheritanceFragment derivedSuperFragment : derivedSuperFragments) {
						CompleteInheritance superInheritance = derivedSuperFragment.getBaseInheritance();
						InheritanceFragment superFragment = superInheritance.getFragment(baseInheritance);
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
								else if (!bestInheritance.isSubInheritanceOf(superInheritance)) {	// Non-occluded child candidate
									bestOverload = null;
									depth = -1;
									break;
								}
							}
						}
					}
				}
				if (bestOverload != null) {
					libraryFeature = PivotUtilInternal.getImplementation(bestOverload);
				}
				else {
					libraryFeature = OclAnyUnsupportedOperation.AMBIGUOUS;
				}
			}
			if (libraryFeature == null) {
				libraryFeature = OclAnyUnsupportedOperation.INSTANCE;
			}
			operationMap.put(apparentOperation, libraryFeature);
			return libraryFeature;
		}
	}

	@Override
	public @NonNull Iterable<? extends Operation> getLocalOperations() {
		return operationMap != null ? operationMap.keySet() : Collections.<Operation>emptyList();
	}
	
	@Override
	public @NonNull Iterable<? extends Property> getLocalProperties() {
		return propertyMap != null ? propertyMap.keySet() : Collections.<Property>emptyList();
	}
}