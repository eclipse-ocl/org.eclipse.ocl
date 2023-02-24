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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.InheritanceFragment;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.ParametersId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.elements.AbstractExecutorNamedElement;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.UnsupportedOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

public abstract class AbstractInheritance extends AbstractExecutorNamedElement implements CompleteInheritance
{
	public static final int ORDERED = FlatClass.ORDERED;
	public static final int UNIQUE = FlatClass.UNIQUE;
	public static final int OCL_ANY = FlatClass.OCL_ANY;
	public static final int OCL_VOID = FlatClass.OCL_VOID;
	public static final int OCL_INVALID = FlatClass.OCL_INVALID;
	/**
	 * @since 1.1
	 */
	public static final int ABSTRACT = FlatClass.ABSTRACT;

	/**
	 * A simple public static method that may be used to force class initialization.
	 */
	public static void initStatics() {}

	protected final @NonNull FlatClass flatClass;

	public AbstractInheritance(@NonNull String name, int flags) {
		super(name);
		this.flatClass = new FlatClass(this, name, flags);
	}

	@Override
	public @NonNull CompleteInheritance getCommonInheritance(@NonNull CompleteInheritance thatInheritance) {
		return flatClass.getCommonInheritance(thatInheritance);
	}

	@Override
	public @NonNull FlatClass getFlatClass() {
		return flatClass;
	}

	@Override
	public @Nullable InheritanceFragment getFragment(@NonNull CompleteInheritance thatInheritance) {
		int staticDepth = thatInheritance.getDepth();
		if (staticDepth <= getDepth()) {
			int iMax = getIndex(staticDepth+1);
			for (int i = getIndex(staticDepth); i < iMax; i++) {
				InheritanceFragment fragment = getFragment(i);
				if (fragment.getBaseFlatClass() == thatInheritance) {
					return fragment;
				}
			}
		}
		return null;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getType() {
		return getPivotClass();
	}

	public final boolean isInvalid() {
		return flatClass.isInvalid();
	}

	@Override
	public final boolean isOclAny() {
		return flatClass.isOclAny();
	}

//	@Override
	public /*final*/ boolean isOrdered() {
		return flatClass.isOrdered();
	}

	@Override
	public boolean isSubInheritanceOf(@NonNull CompleteInheritance thatInheritance) {
		return flatClass.isSubFlatClassOf(thatInheritance.getFlatClass());
	}

	@Override
	public boolean isSuperInheritanceOf(@NonNull CompleteInheritance thatInheritance) {
		return flatClass.isSuperFlatClassOf(thatInheritance.getFlatClass());
	}

	@Override
	public final boolean isUndefined() {
		return flatClass.isUndefined();
	}

//	@Override
	public /*final*/ boolean isUnique() {
		return flatClass.isUnique();
	}

	@Override
	public @NonNull Operation lookupActualOperation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		getDepth();
		CompleteInheritance apparentInheritance = apparentOperation.getInheritance(standardLibrary);
		if (apparentInheritance != null) {
			FlatClass apparentFlatClass = apparentInheritance.getFlatClass();
			int apparentDepth = ClassUtil.nonNullModel(apparentInheritance).getDepth();
			if (apparentDepth+1 < getIndexes()) {				// null and invalid may fail here
				int iMax = getIndex(apparentDepth+1);
				for (int i = getIndex(apparentDepth); i < iMax; i++) {
					InheritanceFragment fragment = getFragment(i);
					if (fragment.getBaseFlatClass() == apparentFlatClass) {
						Operation actualOperation = fragment.getActualOperation(apparentOperation);
						return actualOperation;
					}
				}
			}
		}
		return apparentOperation;	// invoke apparent op for null and invalid
	}

	@Override
	public @NonNull LibraryFeature lookupImplementation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		getDepth();
		CompleteInheritance apparentInheritance = apparentOperation.getInheritance(standardLibrary);
		if (apparentInheritance != null) {
			FlatClass apparentFlatClass = apparentInheritance.getFlatClass();
			int apparentDepth = ClassUtil.nonNullModel(apparentInheritance).getDepth();
			if (apparentDepth+1 < getIndexes()) {				// null and invalid may fail here
				int iMax = getIndex(apparentDepth+1);
				for (int i = getIndex(apparentDepth); i < iMax; i++) {
					InheritanceFragment fragment = getFragment(i);
					if (fragment.getBaseFlatClass() == apparentFlatClass) {
						return fragment.getImplementation(apparentOperation);
					}
				}
			}
		}
		LibraryFeature implementation = PivotUtilInternal.getImplementation(apparentOperation);	// invoke apparent op for null and invalid
		if (implementation == null) {
			implementation = UnsupportedOperation.INSTANCE;
		}
		return implementation;
	}

	@Override
	public @Nullable Operation lookupLocalOperation(@NonNull StandardLibrary standardLibrary, @NonNull String operationName, CompleteInheritance... argumentTypes) {
		for (Operation localOperation : getPivotClass().getOwnedOperations()) {
			if (localOperation.getName().equals(operationName)) {
				ParametersId firstParametersId = localOperation.getParametersId();
				int iMax = firstParametersId.size();
				if (iMax == argumentTypes.length) {
					int i = 0;
					for (; i < iMax; i++) {
						TypeId firstParameterId = firstParametersId.get(i);
						assert firstParameterId != null;
						@NonNull Type secondParameterType = argumentTypes[i].getPivotClass();
						if (firstParameterId != secondParameterType.getTypeId()) {
							break;
						}
					}
					if (i >= iMax) {
						return localOperation;
					}
				}
			}
		}
		return null;
	}
}
