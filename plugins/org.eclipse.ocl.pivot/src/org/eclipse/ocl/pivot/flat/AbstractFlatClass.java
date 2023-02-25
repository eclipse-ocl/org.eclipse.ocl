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

import java.util.ArrayList;
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
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.ParametersId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorFragment;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.UnsupportedOperation;
import org.eclipse.ocl.pivot.types.AbstractFragment;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

public abstract class AbstractFlatClass implements FlatClass		// XXX FIXME immutable metamodels
{

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
	private @Nullable Set<@NonNull AbstractFlatClass> knownSubFlatClasses = null;

	protected AbstractFlatClass(/*@Nullable CompleteClass completeClass, @NonNull Type pivotType,*/ @NonNull String name, int flags) {
		this.name = name;
		this.flags = flags;
	}

	public void addSubFlatClass(@NonNull FlatClass subFlatClass) {
		Set<@NonNull AbstractFlatClass> knownSubFlatClasses2 = knownSubFlatClasses;
		if (knownSubFlatClasses2 == null) {
			knownSubFlatClasses = knownSubFlatClasses2 = new HashSet<>();
		}
		knownSubFlatClasses2.add((AbstractFlatClass)subFlatClass);
	}

	protected abstract @NonNull AbstractFragment createFragment(@NonNull FlatClass baseFlatClass);

	/**
	 * Add this FlatClass and all un-installed super-FlatClasses to inheritances, returning true if this
	 * inheritance was already installed.
	 */
	private boolean gatherUninstalledFlatClasses(@NonNull List<@NonNull AbstractFlatClass> flatClasses) {
		boolean gotOne = false;
		if (!flatClasses.contains(this)) {
			flatClasses.add(this);
			if (fragments == null) {
				for (@NonNull FlatClass superFlatClass : getInitialSuperFlatClasses()) {
					if (((AbstractFlatClass)superFlatClass).gatherUninstalledFlatClasses(flatClasses)) {
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
	@Override
	public @NonNull FragmentIterable getAllProperSuperFragments() {
		if (fragments == null) {
			initialize();
		}
		@NonNull InheritanceFragment @NonNull [] fragments2 = ClassUtil.nonNullState(fragments);
		return new FragmentIterable(fragments2, 0, fragments2.length-1);
	}

	@Override
	public @NonNull FragmentIterable getAllSuperFragments() {
		if (fragments == null) {
			initialize();
		}
		return new FragmentIterable(ClassUtil.nonNullState(fragments));
	}

//	@Deprecated
//	public @NonNull CompleteInheritance getCommonInheritance(@NonNull CompleteInheritance thatInheritance) {
//		return getCommonFlatClass(thatInheritance.getFlatClass()).completeInheritance;
//	}

	@Override
	public @NonNull FlatClass getCommonFlatClass(@NonNull FlatClass that) {
		if (this == that) {
			return this;
		}
		if ((flags & (OCL_ANY|OCL_VOID|OCL_INVALID)) != 0) {
			if ((flags & OCL_ANY) != 0) {
				return this;
			}
			else if ((flags & OCL_INVALID) != 0) {
				return that;
			}
			else {
				return that.isUndefined() ? this : that;
			}
		}
		int thatDepth = that.getDepth();
		if ((thatDepth ==  1) && that.isUndefined()) {
			return this;
		}
		int thisDepth = getDepth();
		int staticDepth = Math.min(thisDepth, thatDepth);
		for ( ; staticDepth > 0; --staticDepth) {
			int iMax = getIndex(staticDepth+1);
			int jMax = that.getIndex(staticDepth+1);
			FlatClass commonFlatClass = null;
			int commonFlatClasses = 0;
			for (int i = getIndex(staticDepth); i < iMax; i++) {
				FlatClass thisBaseFlatClass = getFragment(i).getBaseFlatClass();
				for (int j = that.getIndex(staticDepth); j < jMax; j++) {
					FlatClass thatBaseFlatClass = that.getFragment(j).getBaseFlatClass();
					if (thisBaseFlatClass == thatBaseFlatClass) {
						commonFlatClasses++;
						commonFlatClass = thisBaseFlatClass;
						break;
					}
				}
				if (commonFlatClasses > 1) { 				// More than one so must go less deep to find uniqueness
					break;
				}
			}
			if (commonFlatClasses == 1) {					// Must be unique to avoid arbitrary choice for e.g. Sequence{1, 2.0, '3'}->elementType
				assert commonFlatClass != null;
				return commonFlatClass;
			}
		}
		return getFragment(0).getBaseFlatClass();	// Always OclAny at index 0
	}

//	@Deprecated /* @deprecated phase out to enhance modulrity */
//	public @NonNull CompleteInheritance getCompleteInheritance() {
//		return completeInheritance;
//	}

	@Override
	public abstract @NonNull CompleteClass getCompleteClass();

	@Override
	public int getDepth() {
		if (indexes == null) {
			initialize();
		}
		int @Nullable [] indexes2 = indexes;
		assert indexes2 != null;
		return indexes2.length-2;
	}

	@Override
	public @Nullable InheritanceFragment getFragment(@NonNull FlatClass that) {
		int staticDepth = that.getDepth();
		if (staticDepth <= getDepth()) {
			int iMax = getIndex(staticDepth+1);
			for (int i = getIndex(staticDepth); i < iMax; i++) {
				InheritanceFragment fragment = getFragment(i);
				FlatClass baseFlatClass = fragment.getBaseFlatClass();
				if (baseFlatClass == that) {
					return fragment;
				}
			}
		}
		return null;
	}

	@Override
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

	protected @NonNull IdResolver getIdResolver() {
		throw new UnsupportedOperationException();		// FIXME
	}

	@Override
	public int getIndex(int fragmentNumber) {
		int @Nullable [] indexes2 = indexes;
		assert indexes2 != null;
		return indexes2[fragmentNumber];
	}

	@Override
	public int getIndexes(){
		int @Nullable [] indexes2 = indexes;
		assert indexes2 != null;
		return indexes2.length;
	}

	/**
	 * Return the immediate superinheritances without reference to the fragments.
	 */
	protected abstract @NonNull Iterable<@NonNull FlatClass> getInitialSuperFlatClasses();

	@Override
	public @NonNull String getName() {
		return name;
	}

//	public abstract org.eclipse.ocl.pivot.@NonNull Class getPivotClass();

	@Override
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


	protected @NonNull StandardLibrary getStandardLibrary() {
		throw new UnsupportedOperationException();					// FIXME
	}

	@Override
	public final @NonNull FragmentIterable getSuperFragments(int depth) {
		return new FragmentIterable(ClassUtil.nonNullState(fragments), indexes[depth], indexes[depth+1]);
	}

/*	@Override
	public final @NonNull FragmentIterable getSuperFragments(int depth) {
		int @Nullable [] indexes2 = indexes;
		assert indexes2 != null;
		return new FragmentIterable(ClassUtil.nonNullState(fragments), indexes2[depth], indexes2[depth+1]);
	} */

	@Override
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
		List<@NonNull AbstractFlatClass> uninstalledFlatClasses = new ArrayList<>();
		// Detect missing OclAny inheritance
		// - any installed superclass must inherit from OclAny so ok.
		// - an all-uninstalled superclass list must include OclAny to be ok.
		if (!gatherUninstalledFlatClasses(uninstalledFlatClasses)) {
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
		@SuppressWarnings("unused") List<@NonNull AbstractFlatClass> debugOldUninstalledFlatClasses = new ArrayList<>(uninstalledFlatClasses);
		while (true) {
			Boolean gotOne = false;
			for (Iterator<@NonNull AbstractFlatClass> it = uninstalledFlatClasses.listIterator(); it.hasNext(); ) {
				@NonNull AbstractFlatClass uninstalledFlatClass = it.next();
				if (uninstalledFlatClass.isInstallable()) {
					uninstalledFlatClass.install();
					it.remove();
					gotOne = true;
				}
			}
			if (uninstalledFlatClasses.isEmpty()) {
				break;
			}
			//			int newPendingCount = uninstalledInheritances.size();
			if (!gotOne) {
				List<@NonNull AbstractFlatClass> debugNewUninstalledFlatClasses = new ArrayList<>();
				gatherUninstalledFlatClasses(debugNewUninstalledFlatClasses);
				StringBuilder s = new StringBuilder();
				s.append("FlatClass loop for "); //$NON-NLS-1$
				for (ListIterator<@NonNull AbstractFlatClass> it = uninstalledFlatClasses.listIterator(); it.hasNext(); ) {
					@NonNull AbstractFlatClass uninstalledFlatClass = it.next();
					if (!uninstalledFlatClass.isInstallable()) {
						s.append("\n  "); //$NON-NLS-1$
						s.append(uninstalledFlatClass);
					}
				}
				throw new IllegalStateException(s.toString());
			}
			//			oldPendingCount = newPendingCount;
		}
	}

	/**
	 * Install this FlatClass establishing its superClass tables and registering
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
			for (@NonNull FlatClass superFlatClass : getInitialSuperFlatClasses()) {
				//				installIn(superInheritance, this, all);
				int j = 0;
				for (int i = 0; i < superFlatClass.getIndexes()-1; i++) {
					List<@NonNull FlatClass> some = (i < all.size()) ? all.get(i) : null;
					if (some == null) {
						some = new ArrayList<>();
						all.add(some);
					}
					int jMax = superFlatClass.getIndex(i+1);
					for (; j < jMax; j++) {
						InheritanceFragment fragment = superFlatClass.getFragment(j);
						FlatClass baseFlatClass = fragment.getBaseFlatClass();
						if (!some.contains(baseFlatClass)) {
							some.add(baseFlatClass);
							((AbstractFlatClass)baseFlatClass).addSubFlatClass(this);
						}
					}
				}
			}
			int superDepths = all.size();
			int superFlatClasses = 0;
			for (List<FlatClass> some : all) {
				superFlatClasses += some.size();
			}
			assert superDepths > 0;
			@NonNull InheritanceFragment @NonNull [] fragments2 = fragments = new @NonNull InheritanceFragment[superFlatClasses+1];	// +1 for OclSelf
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
	 * Install the root OclAny FlatClass.
	 */
	private final void installOclAny() {
		assert fragments == null;
		fragments = new @NonNull InheritanceFragment[] { createFragment(this) };
		indexes = new int[] { 0, 1 };
	}

	@Override
	public boolean isAbstract() {
		return (flags & ABSTRACT) != 0;
	}

	@Override
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
		for (FlatClass superFlatClass : getInitialSuperFlatClasses()) {
			if (!((AbstractFlatClass)superFlatClass).isInstalled()) {
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
	public boolean isInstalled() {
		return fragments != null;
	}

	public final boolean isOclAny() {
		return (flags & OCL_ANY) != 0;
	}

	@Override
	public boolean isOrdered() {
		return (flags & ORDERED) != 0;
	}

	@Override
	public boolean isSubFlatClassOf(@NonNull FlatClass that) {
		int theseFlags = flags & (OCL_VOID|OCL_INVALID);
		int thoseFlags = ((AbstractFlatClass)that).flags & (OCL_VOID|OCL_INVALID);
		if ((theseFlags == 0) && (thoseFlags == 0)) {
			return getFragment(that) != null;
		}
		else {
			return theseFlags >= thoseFlags;
		}
	}

	@Override
	public boolean isSuperFlatClassOf(@NonNull FlatClass that) {
		int theseFlags = flags & (OCL_VOID|OCL_INVALID);
		int thoseFlags = ((AbstractFlatClass)that).flags & (OCL_VOID|OCL_INVALID);
		if ((theseFlags == 0) && (thoseFlags == 0)) {
			return that.getFragment(this) != null;
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
	public boolean isUnique() {
		return (flags & UNIQUE) != 0;
	}

	@Override
	public @NonNull Operation lookupActualOperation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		getDepth();
		CompleteInheritance apparentInheritance = apparentOperation.getInheritance(standardLibrary);
		if (apparentInheritance != null) {
			FlatClass apparentFlatClass = apparentInheritance.getFlatClass();
			int apparentDepth = ClassUtil.nonNullModel(apparentFlatClass).getDepth();
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
			int apparentDepth = ClassUtil.nonNullModel(apparentFlatClass).getDepth();
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
	public @Nullable Operation lookupLocalOperation(@NonNull StandardLibrary standardLibrary, @NonNull String operationName, @NonNull FlatClass... argumentTypes) {
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

	public void removeSubFlatClass(@NonNull FlatClass subFlatClass) {
		if (knownSubFlatClasses != null) {
			knownSubFlatClasses.remove(subFlatClass);
		}
	}

	@Override
	public void uninstall() {
		@NonNull InheritanceFragment @Nullable [] fragments2 = fragments;
		boolean isNonNull = fragments2 != null;		// FIXME needed for JDT 4.5, not needed for JDT 4.6M4
		if (isNonNull && (fragments2 != null)) {
			//			System.out.println("Uninstall " + this);
			for (InheritanceFragment fragment : fragments2) {
				AbstractFlatClass baseFlatClass = (AbstractFlatClass)fragment.getBaseFlatClass();
				baseFlatClass.removeSubFlatClass(this);
			}
			fragments = null;
			indexes = null;
		}
		if (knownSubFlatClasses != null) {
			Set<@NonNull AbstractFlatClass> previouslyKnownSubFlatClasses = knownSubFlatClasses;
			knownSubFlatClasses = null;
			for (@NonNull FlatClass subFlatClass : previouslyKnownSubFlatClasses) {
				((CompleteClassInternal)subFlatClass.getCompleteClass()).uninstall();
				subFlatClass.uninstall();
			}
		}
	}
}
