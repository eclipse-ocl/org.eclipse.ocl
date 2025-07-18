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
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.flat.FlatFragment;
import org.eclipse.ocl.pivot.ids.ParametersId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.elements.AbstractExecutorNamedElement;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.UnsupportedOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

public abstract class AbstractInheritance extends AbstractExecutorNamedElement implements FlatClass
{
	/**
	 * A simple public static method that may be used to force class initialization.
	 */
	public static void initStatics() {}

	protected final int flags;
	//	protected @Nullable Map<String, DomainOperation> operationMap = null;
	//	protected @Nullable Map<String, DomainProperty> propertyMap = null;

	public AbstractInheritance(@NonNull String name, int flags) {
		super(name);
		this.flags = flags;
	}

	@Override
	public @NonNull FlatClass getCommonFlatClass(@NonNull FlatClass thatInheritance) {
		if (this == thatInheritance) {
			return this;
		}
		if ((flags & (OCL_ANY|OCL_VOID|OCL_INVALID)) != 0) {
			if ((flags & OCL_ANY) != 0) {
				return this;
			}
			else if ((flags & OCL_INVALID) != 0) {
				return thatInheritance;
			}
			else {
				return thatInheritance.isUndefined() ? this : thatInheritance;
			}
		}
		int thatDepth = thatInheritance.getDepth();
		if ((thatDepth ==  1) && thatInheritance.isUndefined()) {
			return this;
		}
		int thisDepth = getDepth();
		int staticDepth = Math.min(thisDepth, thatDepth);
		for ( ; staticDepth > 0; --staticDepth) {
			int iMax = getIndex(staticDepth+1);
			int jMax = thatInheritance.getIndex(staticDepth+1);
			FlatClass commonInheritance = null;
			int commonInheritances = 0;
			for (int i = getIndex(staticDepth); i < iMax; i++) {
				FlatClass thisBaseInheritance = getFragment(i).getBaseFlatClass();
				for (int j = thatInheritance.getIndex(staticDepth); j < jMax; j++) {
					FlatClass thatBaseInheritance = thatInheritance.getFragment(j).getBaseFlatClass();
					if (thisBaseInheritance == thatBaseInheritance) {
						commonInheritances++;
						commonInheritance = thisBaseInheritance;
						break;
					}
				}
				if (commonInheritances > 1) { 				// More than one so must go less deep to find uniqueness
					break;
				}
			}
			if (commonInheritances == 1) {					// Must be unique to avoid arbitrary choice for e.g. Sequence{1, 2.0, '3'}->elementType
				assert commonInheritance != null;
				return commonInheritance;
			}
		}
		return getFragment(0).getBaseFlatClass();	// Always OclAny at index 0
	}

	@Override
	public void initFragments(@NonNull FlatFragment @NonNull [] fragments, int @NonNull [] depthCounts) {
		throw new UnsupportedOperationException();
	}

	@Override
	public final boolean isInvalid() {
		return (flags & OCL_INVALID) != 0;
	}

	@Override
	public final boolean isOclAny() {
		return (flags & OCL_ANY) != 0;
	}

	@Override
	public boolean isSubFlatClassOf(@NonNull FlatClass thatInheritance) {
		int theseFlags = flags & (OCL_VOID|OCL_INVALID);
		int thoseFlags = ((AbstractInheritance)thatInheritance).flags & (OCL_VOID|OCL_INVALID);
		if ((theseFlags == 0) && (thoseFlags == 0)) {
			return getFragment(thatInheritance) != null;
		}
		else {
			return theseFlags >= thoseFlags;
		}
	}

	@Override
	public boolean isSuperFlatClassOf(@NonNull FlatClass thatInheritance) {
		int theseFlags = flags & (OCL_VOID|OCL_INVALID);
		int thoseFlags = ((AbstractInheritance)thatInheritance).flags & (OCL_VOID|OCL_INVALID);
		if ((theseFlags == 0) && (thoseFlags == 0)) {
			return thatInheritance.getFragment(this) != null;
		}
		else {
			return theseFlags <= thoseFlags;
		}
	}

	@Override
	public final boolean isUndefined() {
		return (flags & (OCL_VOID|OCL_INVALID)) != 0;
	}

	@Override
	public @NonNull Operation lookupActualOperation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		getDepth();
		FlatClass apparentInheritance = apparentOperation.getFlatClass(standardLibrary);
		int apparentDepth = ClassUtil.requireNonNull(apparentInheritance).getDepth();
		if (apparentDepth+1 < getIndexes()) {				// null and invalid may fail here
			int iMax = getIndex(apparentDepth+1);
			for (int i = getIndex(apparentDepth); i < iMax; i++) {
				FlatFragment fragment = getFragment(i);
				if (fragment.getBaseFlatClass() == apparentInheritance) {
					Operation actualOperation = fragment.getActualOperation(apparentOperation);
					return actualOperation;
				}
			}
		}
		return apparentOperation;	// invoke apparent op for null and invalid
	}

	@Override
	public @NonNull LibraryFeature lookupImplementation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		getDepth();
		FlatClass apparentInheritance = apparentOperation.getFlatClass(standardLibrary);
		int apparentDepth = ClassUtil.requireNonNull(apparentInheritance).getDepth();
		if (apparentDepth+1 < getIndexes()) {				// null and invalid may fail here
			int iMax = getIndex(apparentDepth+1);
			for (int i = getIndex(apparentDepth); i < iMax; i++) {
				FlatFragment fragment = getFragment(i);
				if (fragment.getBaseFlatClass() == apparentInheritance) {
					return fragment.getImplementation(apparentOperation);
				}
			}
		}
		LibraryFeature implementation = PivotUtil.getImplementation(apparentOperation);	// invoke apparent op for null and invalid
		if (implementation == null) {
			implementation = UnsupportedOperation.INSTANCE;
		}
		return implementation;
	}

	@Override
	public @Nullable Operation lookupLocalOperation(@NonNull StandardLibrary standardLibrary, @NonNull String operationName, FlatClass... argumentTypes) {
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
