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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.InheritanceFragment;
import org.eclipse.ocl.pivot.internal.complete.CompleteInheritanceImpl;
import org.eclipse.ocl.pivot.internal.library.executor.ReflectiveInheritance;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.IndexableIterable;
import org.eclipse.ocl.pivot.utilities.Nameable;

public class FlatClass implements Nameable
{
	public static class FragmentIterable implements IndexableIterable<@NonNull InheritanceFragment>
	{
		protected class Iterator implements java.util.Iterator<@NonNull InheritanceFragment>
		{
			private int index = firstIndex;

			@Override
			public boolean hasNext() {
				return index < lastIndex;
			}

			@Override
			public @NonNull InheritanceFragment next() {
				return array[index++];
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		}

		private final @NonNull InheritanceFragment @NonNull [] array;
		private final int firstIndex;
		private final int lastIndex;

		public FragmentIterable(@NonNull InheritanceFragment @NonNull [] array) {
			this.array = array;
			this.firstIndex = 0;
			this.lastIndex = array.length;
		}

		public FragmentIterable(@NonNull InheritanceFragment @NonNull [] array, int firstIndex, int lastIndex) {
			this.array = array;
			this.firstIndex = firstIndex;
			this.lastIndex = lastIndex;
		}

		@Override
		public @NonNull InheritanceFragment get(int index) {
			return ClassUtil.nonNullState(array[firstIndex + index]);
		}

		@Override
		public java.util.@NonNull Iterator<@NonNull InheritanceFragment> iterator() {
			return new Iterator();
		}

		@Override
		public int size() {
			return lastIndex - firstIndex;
		}

		@Override
		public String toString() {
			StringBuilder s = null;
			for (int i = firstIndex; i < lastIndex; i++) {
				if (s == null) {
					s = new StringBuilder();
					s.append("[");
				}
				else {
					s.append(", ");
				}
				s.append(array[i]);
			}
			if (s == null) {
				return "";
			}
			s.append("]");
			return s.toString();
		}
	}

	public static final int ORDERED = 1 << 0;
	public static final int UNIQUE = 1 << 1;
	public static final int OCL_ANY = 1 << 2;
	public static final int OCL_VOID = 1 << 3;
	public static final int OCL_INVALID = 1 << 4;			// NB. OCL_INVALID assumed greater than OCL_VOID by isSuper/SubInheritanceOf
	/**
	 * @since 1.1
	 */
	public static final int ABSTRACT = 1 << 5;

	protected final @NonNull CompleteInheritance completeInheritance;

	protected final @NonNull String name;
	protected final int flags;
	//	protected @Nullable Map<String, DomainOperation> operationMap = null;
	//	protected @Nullable Map<String, DomainProperty> propertyMap = null;

	/**
	 * The Inheritances of sub-types that have been installed, and which must be
	 * uninstalled in the event of an inheritance change for this Inheritance.
	 */
	private Set<@NonNull FlatClass> knownSubInheritances = null;


	public FlatClass(@NonNull CompleteInheritance completeInheritance, @NonNull String name, int flags) {
		this.completeInheritance = completeInheritance;
		this.name = name;
		this.flags = flags;
	}

	public void addSubInheritance(@NonNull FlatClass subInheritance) {
		if (knownSubInheritances == null) {
			knownSubInheritances = new HashSet<>();
		}
		knownSubInheritances.add(subInheritance);
	}

	public @NonNull CompleteInheritance getCommonInheritance(@NonNull CompleteInheritance thatInheritance) {
		FlatClass that = thatInheritance.getFlatClass();
		if (this == that) {
			return this.completeInheritance;
		}
		if ((flags & (OCL_ANY|OCL_VOID|OCL_INVALID)) != 0) {
			if ((flags & OCL_ANY) != 0) {
				return this.completeInheritance;
			}
			else if ((flags & OCL_INVALID) != 0) {
				return that.completeInheritance;
			}
			else {
				return that.isUndefined() ? this.completeInheritance : that.completeInheritance;
			}
		}
		int thatDepth = that.getDepth();
		if ((thatDepth ==  1) && that.isUndefined()) {
			return this.completeInheritance;
		}
		int thisDepth = getDepth();
		int staticDepth = Math.min(thisDepth, thatDepth);
		for ( ; staticDepth > 0; --staticDepth) {
			int iMax = getIndex(staticDepth+1);
			int jMax = that.getIndex(staticDepth+1);
			FlatClass commonFlatClass = null;
			int commonInheritances = 0;
			for (int i = getIndex(staticDepth); i < iMax; i++) {
				FlatClass thisBaseFlatClass = getFragment(i).getBaseFlatClass();
				for (int j = that.getIndex(staticDepth); j < jMax; j++) {
					FlatClass thatBaseFlatClass = that.getFragment(j).getBaseFlatClass();
					if (thisBaseFlatClass == thatBaseFlatClass) {
						commonInheritances++;
						commonFlatClass = thisBaseFlatClass;
						break;
					}
				}
				if (commonInheritances > 1) { 				// More than one so must go less deep to find uniqueness
					break;
				}
			}
			if (commonInheritances == 1) {					// Must be unique to avoid arbitrary choice for e.g. Sequence{1, 2.0, '3'}->elementType
				assert commonFlatClass != null;
				return commonFlatClass.completeInheritance;
			}
		}
		return getFragment(0).getBaseFlatClass().completeInheritance;	// Always OclAny at index 0
	}

	@Deprecated /* @deprecated phase out to enhance modulrity */
	public @NonNull CompleteInheritance getCompleteInheritance() {
		return completeInheritance;
	}

	public @NonNull CompleteClass getCompleteClass() {
		return ((CompleteInheritanceImpl)completeInheritance).getCompleteClass();			// FIXME cast
	}

	public int getDepth() {
		return completeInheritance.getDepth();
	}

	public @Nullable InheritanceFragment getFragment(@NonNull FlatClass that) {
		int staticDepth = that.getDepth();
		if (staticDepth <= getDepth()) {
			int iMax = getIndex(staticDepth+1);
			for (int i = getIndex(staticDepth); i < iMax; i++) {
				InheritanceFragment fragment = getFragment(i);
				FlatClass baseInheritance = fragment.getBaseFlatClass();
				if (baseInheritance == that) {
					return fragment;
				}
			}
		}
		return null;
	}

	private @NonNull InheritanceFragment getFragment(int i) {
		return completeInheritance.getFragment(i);
	}

	private int getIndex(int i) {
		return completeInheritance.getIndex(i);
	}

//	private int getIndexes() {
//		return completeInheritance.getIndexes();
//	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	public org.eclipse.ocl.pivot.@NonNull Class getPivotClass() {
		return completeInheritance.getPivotClass();
	}

	public @NonNull InheritanceFragment getSelfFragment() {
		return completeInheritance.getSelfFragment();
	}

//	public org.eclipse.ocl.pivot.@NonNull Class getType() {
//		return completeInheritance.getPivotClass();
//	}

	public @NonNull IndexableIterable<@NonNull InheritanceFragment> getSuperFragments(int depth) {
		return completeInheritance.getSuperFragments(depth);
	}

	public boolean isAbstract() {
		return (flags & ABSTRACT) != 0;
	}

	public final boolean isInvalid() {
		return (flags & OCL_INVALID) != 0;
	}

	public final boolean isOclAny() {
		return (flags & OCL_ANY) != 0;
	}

	public boolean isOrdered() {
		return (flags & ORDERED) != 0;
	}

	public boolean isSubFlatClassOf(@NonNull FlatClass that) {
		int theseFlags = flags & (OCL_VOID|OCL_INVALID);
		int thoseFlags = that.flags & (OCL_VOID|OCL_INVALID);
		if ((theseFlags == 0) && (thoseFlags == 0)) {
			return getFragment(that) != null;
		}
		else {
			return theseFlags >= thoseFlags;
		}
	}

	public boolean isSuperFlatClassOf(@NonNull FlatClass that) {
		int theseFlags = flags & (OCL_VOID|OCL_INVALID);
		int thoseFlags = that.flags & (OCL_VOID|OCL_INVALID);
		if ((theseFlags == 0) && (thoseFlags == 0)) {
			return that.getFragment(this) != null;
		}
		else {
			return theseFlags <= thoseFlags;
		}
	}

	public final boolean isUndefined() {
		return (flags & (OCL_VOID|OCL_INVALID)) != 0;
	}

	public boolean isUnique() {
		return (flags & UNIQUE) != 0;
	}

/*	public @NonNull Operation lookupActualOperation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		getDepth();
		CompleteInheritance apparentInheritance = apparentOperation.getInheritance(standardLibrary);
		int apparentDepth = ClassUtil.nonNullModel(apparentInheritance).getDepth();
		if (apparentDepth+1 < getIndexes()) {				// null and invalid may fail here
			int iMax = getIndex(apparentDepth+1);
			for (int i = getIndex(apparentDepth); i < iMax; i++) {
				InheritanceFragment fragment = getFragment(i);
				if (fragment.getBaseInheritance() == apparentInheritance) {
					Operation actualOperation = fragment.getActualOperation(apparentOperation);
					return actualOperation;
				}
			}
		}
		return apparentOperation;	// invoke apparent op for null and invalid
	} */

/*	public @NonNull LibraryFeature lookupImplementation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		getDepth();
		CompleteInheritance apparentInheritance = apparentOperation.getInheritance(standardLibrary);
		int apparentDepth = ClassUtil.nonNullModel(apparentInheritance).getDepth();
		if (apparentDepth+1 < getIndexes()) {				// null and invalid may fail here
			int iMax = getIndex(apparentDepth+1);
			for (int i = getIndex(apparentDepth); i < iMax; i++) {
				InheritanceFragment fragment = getFragment(i);
				if (fragment.getBaseInheritance() == apparentInheritance) {
					return fragment.getImplementation(apparentOperation);
				}
			}
		}
		LibraryFeature implementation = PivotUtilInternal.getImplementation(apparentOperation);	// invoke apparent op for null and invalid
		if (implementation == null) {
			implementation = UnsupportedOperation.INSTANCE;
		}
		return implementation;
	} */

/*	public @Nullable Operation lookupLocalOperation(@NonNull StandardLibrary standardLibrary, @NonNull String operationName, CompleteInheritance... argumentTypes) {
		for (Operation localOperation : completeInheritance.getPivotClass().getOwnedOperations()) {
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
	} */

	public void removeSubInheritance(@NonNull FlatClass subInheritance) {
		if (knownSubInheritances != null) {
			knownSubInheritances.remove(subInheritance);
		}
	}

	@Override
	public String toString() {
		return completeInheritance.getPivotClass().toString();
	}

	public void uninstall() {
		if (knownSubInheritances != null) {
			Set<@NonNull FlatClass> previouslyKnownSubInheritances = knownSubInheritances;
			knownSubInheritances = null;
			for (FlatClass subInheritance : previouslyKnownSubInheritances) {
				CompleteInheritance completeInheritance2 = subInheritance.getCompleteInheritance();
				if (completeInheritance2 instanceof ReflectiveInheritance) {
					((ReflectiveInheritance)completeInheritance2).uninstall();
				}
			}
		}
	}
}
