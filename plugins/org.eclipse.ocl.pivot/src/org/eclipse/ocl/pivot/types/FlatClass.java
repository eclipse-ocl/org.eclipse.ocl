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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.InheritanceFragment;
import org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal;
import org.eclipse.ocl.pivot.internal.complete.CompleteInheritanceImpl;
import org.eclipse.ocl.pivot.internal.executor.PivotReflectiveFragment;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorFragment;
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
	 * Depth ordered inheritance fragments. OclAny at depth 0, OclSelf at depth size-1.
	 */
	private @NonNull InheritanceFragment @Nullable [] fragments = null;

	/**
	 * The index in fragments at which inheritance fragments at a given depth start.
	 * depthIndexes[0] is always zero since OclAny is always at depth 0.
	 * depthIndexes[depthIndexes.length-2] is always depthIndexes.length-1 since OclSelf is always at depth depthIndexes.length-2.
	 * depthIndexes[depthIndexes.length-1] is always depthIndexes.length to provide an easy end stop.
	 */
	private int[] indexes = null;

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

	private @NonNull AbstractFragment createFragment(@NonNull FlatClass baseFlatClass) {
		return new PivotReflectiveFragment(this, baseFlatClass);
	}

	/**
	 * Add this Inheritance and all un-installed super-Inheritances to inheritances, returning true if this
	 * inheritance was already installed.
	 */
	private boolean gatherUninstalledInheritances(@NonNull List<@NonNull FlatClass> flatClasses) {
		boolean gotOne = false;
		if (!flatClasses.contains(this)) {
			flatClasses.add(this);
			if (fragments == null) {
				for (@NonNull FlatClass superInheritance : getInitialSuperFlatClasses()) {
					if (superInheritance.gatherUninstalledInheritances(flatClasses)) {
						gotOne = true;		// Transitively installed
					}
					else {
						gotOne = true;			// Statically installed
					}
				}
			}
			else {
				gotOne = true;					// Locally installed
			}
		}
		return gotOne;
	}

	/**
	 * Return a depth ordered, OclAny-first, OclSelf-last, Iterable of all the super-adapters excluding this one.
	 */
	public @NonNull FragmentIterable getAllProperSuperFragments() {
		if (fragments == null) {
			initialize();
		}
		@NonNull InheritanceFragment @NonNull [] fragments2 = ClassUtil.nonNullState(fragments);
		return new FragmentIterable(fragments2, 0, fragments2.length-1);
	}

	/**
	 * Return a depth ordered, OclAny-first, OclSelf-last, Iterable of all the super-adapters including this one.
	 */
	public @NonNull FragmentIterable getAllSuperFragments() {
		if (fragments == null) {
			initialize();
		}
		return new FragmentIterable(ClassUtil.nonNullState(fragments));
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
		if (indexes == null) {
			initialize();
		}
		int @Nullable [] indexes2 = indexes;
		assert indexes2 != null;
		return indexes2.length-2;
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

	public @NonNull InheritanceFragment getFragment(int fragmentNumber) {
		if ((fragments == null) && isOclAny()) {
			installOclAny();
		}
		assert fragments != null;
		return fragments[fragmentNumber];
	//	return new ArrayIterable<@NonNull InheritanceFragment>(fragments);
	}

	public @NonNull Iterable<@NonNull InheritanceFragment> getFragments() {
		@NonNull InheritanceFragment[] fragments2 = fragments;
		if (fragments2 == null) {
			initialize();
			fragments2 = fragments;
			assert fragments2 != null;
		}
		return new FragmentIterable(fragments2);
	//	return ClassUtil.nonNullState(fragments)[fragmentNumber];
	}

	public int getIndex(int fragmentNumber) {
		int @Nullable [] indexes2 = indexes;
		assert indexes2 != null;
		return indexes2[fragmentNumber];
	}

	public int getIndexes(){
		int @Nullable [] indexes2 = indexes;
		assert indexes2 != null;
		return indexes2.length;
	}

	/**
	 * Return the immediate superinheritances without reference to the fragments.
	 */
	private @NonNull Iterable<@NonNull FlatClass> getInitialSuperFlatClasses() {
		if (isOclAny()) {
			return Collections.EMPTY_LIST;
		}
		return ((CompleteClassInternal)getCompleteClass()).getPartialClasses().getInitialSuperFlatClasses();
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	public org.eclipse.ocl.pivot.@NonNull Class getPivotClass() {
		return completeInheritance.getPivotClass();
	}

	public @NonNull InheritanceFragment getSelfFragment() {
	/*	if (indexes == null) {
			initialize();
		}
		@NonNull InheritanceFragment @Nullable [] fragments2 = fragments;
		assert fragments2 != null;
		InheritanceFragment fragment = getFragment(fragments2.length-1);
	//	if (fragment == null) {
	//		throw new IllegalStateException("No self fragment"); //$NON-NLS-1$
	//	}
		return fragment; */
		return getFragment(ClassUtil.nonNullState(fragments).length-1);
	}


//	public org.eclipse.ocl.pivot.@NonNull Class getType() {
//		return completeInheritance.getPivotClass();
//	}

	public final @NonNull FragmentIterable getSuperFragments(int depth) {
		return new FragmentIterable(ClassUtil.nonNullState(fragments), indexes[depth], indexes[depth+1]);
	}

/*	@Override
	public final @NonNull FragmentIterable getSuperFragments(int depth) {
		int @Nullable [] indexes2 = indexes;
		assert indexes2 != null;
		return new FragmentIterable(ClassUtil.nonNullState(fragments), indexes2[depth], indexes2[depth+1]);
	} */

	public void initFragments(@NonNull ExecutorFragment @NonNull [] fragments, int[] depthCounts) {
		int[] indexes = new int[depthCounts.length+1];
		indexes[0] = 0;
		for (int i = 0; i <  depthCounts.length; i++) {
			indexes[i+1] = indexes[i] + depthCounts[i];
		}
		this.fragments = fragments;
		this.indexes = indexes;
	}

	private synchronized void initialize() {
		List<@NonNull FlatClass> uninstalledInheritances = new ArrayList<>();
		// Detect missing OclAny inheritance
		// - any installed superclass must inherit from OclAny so ok.
		// - an all-uninstalled superclass list must include OclAny to be ok.
		if (!gatherUninstalledInheritances(uninstalledInheritances)) {
			//			boolean containsOclAny = false;
			//			for (DomainInheritance anInheritance : uninstalledInheritances) {
			//				if (anInheritance.isOclAny()) {
			//					containsOclAny = true;
			//					break;
			//				}
			//			}
			//			if (!containsOclAny)  {	// FIXME may be an rather than the OclAny - need a way to find the partial types.
			/*				List<ReflectiveType> uninstalledInheritances2 = new ArrayList<>();
				gatherUninstalledInheritances(uninstalledInheritances2);
				assert uninstalledInheritances.contains(oclAnyInheritance); */
			//			}
		}
		//		int oldPendingCount = uninstalledInheritances.size();
		@SuppressWarnings("unused") List<@NonNull FlatClass> debugOldUninstalledInheritances = new ArrayList<>(uninstalledInheritances);
		while (true) {
			Boolean gotOne = false;
			for (Iterator<@NonNull FlatClass> it = uninstalledInheritances.listIterator(); it.hasNext(); ) {
				@NonNull FlatClass uninstalledInheritance = it.next();
				if (uninstalledInheritance.isInstallable()) {
					uninstalledInheritance.install();
					it.remove();
					gotOne = true;
				}
			}
			if (uninstalledInheritances.isEmpty()) {
				break;
			}
			//			int newPendingCount = uninstalledInheritances.size();
			if (!gotOne) {
				List<@NonNull FlatClass> debugNewUninstalledInheritances = new ArrayList<>();
				gatherUninstalledInheritances(debugNewUninstalledInheritances);
				StringBuilder s = new StringBuilder();
				s.append("Inheritance loop for "); //$NON-NLS-1$
				for (ListIterator<FlatClass> it = uninstalledInheritances.listIterator(); it.hasNext(); ) {
					FlatClass uninstalledInheritance = it.next();
					if (!uninstalledInheritance.isInstallable()) {
						s.append("\n  "); //$NON-NLS-1$
						s.append(uninstalledInheritance);
					}
				}
				throw new IllegalStateException(s.toString());
			}
			//			oldPendingCount = newPendingCount;
		}
	}

	/**
	 * Install this Inheritance establishing its superClass tables and registering
	 * it to be notified of any changes.
	 *
	 * @return true if installed, false if some superClass uninstallable
	 */
	private boolean install() {
		if (fragments != null) {
			return true;
		}
		//		System.out.println("Install " + this);
		if (isOclAny()) {
			installOclAny();
		}
		else {
			List<@NonNull List<@NonNull FlatClass>> all = new ArrayList<>();
			for (@NonNull FlatClass superInheritance : getInitialSuperFlatClasses()) {
				//				installIn(superInheritance, this, all);
				int j = 0;
				for (int i = 0; i < superInheritance.getIndexes()-1; i++) {
					List<@NonNull FlatClass> some = (i < all.size()) ? all.get(i) : null;
					if (some == null) {
						some = new ArrayList<>();
						all.add(some);
					}
					int jMax = superInheritance.getIndex(i+1);
					for (; j < jMax; j++) {
						InheritanceFragment fragment = superInheritance.getFragment(j);
						FlatClass baseInheritance = fragment.getBaseFlatClass();
						if (!some.contains(baseInheritance)) {
							some.add(baseInheritance);
							baseInheritance.addSubInheritance(this);
						}
					}
				}
			}
			int superDepths = all.size();
			int superInheritances = 0;
			for (List<FlatClass> some : all) {
				superInheritances += some.size();
			}
			assert superDepths > 0;
			@NonNull InheritanceFragment @NonNull [] fragments2 = fragments = new @NonNull InheritanceFragment[superInheritances+1];	// +1 for OclSelf
			int @NonNull [] indexes2 = indexes = new int[superDepths+2];		// +1 for OclSelf, +1 for tail pointer
			int j = 0;
			indexes2[0] = 0;
			for (int i = 0; i < superDepths; i++) {
				for (FlatClass some : all.get(i)) {
					fragments2[j++] = createFragment(some);
				}
				indexes2[i+1] = j;
			}
			indexes2[superDepths++] = j;
			fragments2[j++] = createFragment(this);
			indexes2[superDepths++] = j;
		}
		return true;
	}

	/**
	 * Install the root OclAny Inheritance.
	 */
	private final void installOclAny() {
		assert fragments == null;
		fragments = new @NonNull InheritanceFragment[] { createFragment(this) };
		indexes = new int[] { 0, 1 };
	}

	public boolean isAbstract() {
		return (flags & ABSTRACT) != 0;
	}

	public final boolean isInvalid() {
		return (flags & OCL_INVALID) != 0;
	}

	/**
	 * Return true if this is installed or able to be installed. Returns false if some superclass
	 * must be installed first.
	 */
	private boolean isInstallable() {
		if (isOclAny()) {
			return true;
		}
		if (fragments != null) {
			//			System.out.println("isInstallable true (already) " + this);
			return true;
		}
		//		DomainInheritance oclAnyInheritance = getOclAnyInheritance();
		for (FlatClass superInheritance : getInitialSuperFlatClasses()) {
			if (!superInheritance.isInstalled()) {
				//				System.out.println("isInstallable false " + this);
				return false;
			}
		}
		//		System.out.println("isInstallable true " + this);
		return true;
	}

	/**
	 * Return true if this is installed.
	 */
	private boolean isInstalled() {
		return fragments != null;
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
