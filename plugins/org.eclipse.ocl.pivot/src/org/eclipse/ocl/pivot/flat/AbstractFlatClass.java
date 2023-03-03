/*******************************************************************************
 * Copyright (c) 2023 Willink Transformations and others.
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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteClass;
//import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.InheritanceFragment;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.ParametersId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorFragment;
import org.eclipse.ocl.pivot.internal.library.executor.JavaType;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.UnsupportedOperation;
import org.eclipse.ocl.pivot.types.AbstractFragment;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;

public abstract class AbstractFlatClass implements FlatClass
{
	public static int computeFlags(@NonNull Type asType) {
		if (asType instanceof JavaType) {
			return 0;			// XXX Avoid UOE from getTypeId().
		}
		int flags = 0;
		if (asType instanceof CollectionType) {
			CollectionType collectionType = (CollectionType)asType;
			if (collectionType.isOrdered()) {
				flags |= ORDERED;
			}
			if (collectionType.isUnique()) {
				flags |= UNIQUE;
			}
		}
		TypeId typeId = asType.getTypeId();
		if (typeId == TypeId.OCL_ANY){
			flags |= OCL_ANY;
		}
		else if (typeId == TypeId.OCL_VOID){
			flags |= OCL_VOID;
		}
		else if (typeId == TypeId.OCL_INVALID){
			flags |= OCL_INVALID;
		}
		if ((asType instanceof org.eclipse.ocl.pivot.Class) && ((org.eclipse.ocl.pivot.Class)asType).isIsAbstract()) {
			flags |= ABSTRACT;
		}
		return flags;
	}

	protected final @NonNull FlatModel flatModel;
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
	 * The sub-FlatClasses that have been installed, and which must be
	 * invalidated in the event of an inheritance change for this FlatClass.
	 */
	private @Nullable Set<@NonNull FlatClass> subFlatClasses = null;

	protected AbstractFlatClass(@NonNull FlatModel flatModel, @NonNull String name, int flags) {
		this.flatModel = flatModel;
		this.name = name;
		this.flags = flags;
		if ("OclAny".equals(name)) {
			getClass();		// XXX
		}
		System.out.println("ctor " + NameUtil.debugSimpleName(this) + " : " + name + " " + Integer.toHexString(flags));
	}

	private void addSubFlatClass(@NonNull FlatClass subFlatClass) {
		Set<@NonNull FlatClass> subFlatClasses2 = subFlatClasses;
		if (subFlatClasses2 == null) {
			subFlatClasses = subFlatClasses2 = new HashSet<>();
		}
		subFlatClasses2.add(subFlatClass);
	}

	/**
	 * Return the immediate super-FlatClasses without reference to the fragments.
	 * This method is never invoked for OclAny, consequently there is always at least one direct super-FlatClass.
	 */
	protected abstract @NonNull Iterable<@NonNull FlatClass> computeDirectSuperFlatClasses();

	protected abstract @NonNull AbstractFragment createFragment(@NonNull FlatClass baseFlatClass);

	/**
	 * Populate the keys of flatClass2superFlatClasses with FlatClasses (including this FlatClass) within
	 * the super-FlatClass hierarchy that have no fragemnts describing thir super-FlatClasses. The corresponding
	 * values identify the direct super-FlatClasses.
	 */
	private void gatherFragmentlessSuperFlatClasses(@NonNull Map<@NonNull FlatClass, @NonNull Iterable<@NonNull FlatClass>> flatClass2superFlatClasses) {
		if ((fragments == null) && !flatClass2superFlatClasses.containsKey(this)) {
			Iterable<@NonNull FlatClass> superFlatClasses;
			if (isOclAny()) {
			//	StandardLibrary standardLibrary = getStandardLibrary();
			//	org.eclipse.ocl.pivot.@NonNull Class superClass = standardLibrary.getOclAnyType();
			//	FlatClass superFlatClass = superClass.getFlatClass(standardLibrary);
			//	assert this == superFlatClass;
				superFlatClasses = Collections.emptyList();
			}
			else {
				superFlatClasses = computeDirectSuperFlatClasses();
			}
			flatClass2superFlatClasses.put(this, superFlatClasses);
			for (@NonNull FlatClass superFlatClass : superFlatClasses) {
				((AbstractFlatClass)superFlatClass).gatherFragmentlessSuperFlatClasses(flatClass2superFlatClasses);
			}
		}
	}

	/**
	 * Return a depth ordered, OclAny-first, OclSelf-last, Iterable of all the super-adapters excluding this one.
	 */
	@Override
	public @NonNull FragmentIterable getAllProperSuperFragments() {
		if (fragments == null) {
			initFragments();
		}
		@NonNull InheritanceFragment @NonNull [] fragments2 = ClassUtil.nonNullState(fragments);
		return new FragmentIterable(fragments2, 0, fragments2.length-1);
	}

	@Override
	public @NonNull FragmentIterable getAllSuperFragments() {
		if (fragments == null) {
			initFragments();
		}
		return new FragmentIterable(ClassUtil.nonNullState(fragments));
	}

	@Override
	public @Nullable Operation getBestOverload(@NonNull FlatClass derivedFlatClass, @NonNull Operation apparentOperation) {
		AbstractFlatClass baseFlatClass = this;
		Operation bestOverload = null;
		FlatClass bestFlatClass = null;
		int bestDepth = -1;
		int minDepth = baseFlatClass.getDepth();
		for (int depth = derivedFlatClass.getDepth()-1; depth >= minDepth; depth--) {
			Iterable<@NonNull InheritanceFragment> derivedSuperFragments = derivedFlatClass.getSuperFragments(depth);
			for (InheritanceFragment derivedSuperFragment : derivedSuperFragments) {
				AbstractFlatClass superFlatClass = (AbstractFlatClass)derivedSuperFragment.getBaseFlatClass();
				InheritanceFragment superFragment = superFlatClass.getFragment(baseFlatClass);
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
			return bestOverload;
		}
		else if (bestFlatClass == null) {
			return apparentOperation;		// FIXME Missing operation
		}
		else {
			return null;
		}
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
		@NonNull AbstractFlatClass abstractThat = (AbstractFlatClass)that;
		if ((flags & (OCL_ANY|OCL_VOID|OCL_INVALID)) != 0) {
			if ((flags & OCL_ANY) != 0) {
				return this;
			}
			else if ((flags & OCL_INVALID) != 0) {
				return abstractThat;
			}
			else {
				return abstractThat.isUndefined() ? this : abstractThat;
			}
		}
		int thatDepth = abstractThat.getDepth();
		if ((thatDepth ==  1) && abstractThat.isUndefined()) {
			return this;
		}
		int thisDepth = getDepth();
		int staticDepth = Math.min(thisDepth, thatDepth);
		for ( ; staticDepth > 0; --staticDepth) {
			int iMax = getIndex(staticDepth+1);
			int jMax = abstractThat.getIndex(staticDepth+1);
			FlatClass commonFlatClass = null;
			int commonFlatClasses = 0;
			for (int i = getIndex(staticDepth); i < iMax; i++) {
				FlatClass thisBaseFlatClass = getFragment(i).getBaseFlatClass();
				for (int j = abstractThat.getIndex(staticDepth); j < jMax; j++) {
					FlatClass thatBaseFlatClass = abstractThat.getFragment(j).getBaseFlatClass();
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

	@Override
	public abstract @NonNull CompleteClass getCompleteClass();

	@Override
	public int getDepth() {
		if (indexes == null) {
			initFragments();
		}
		int @Nullable [] indexes2 = indexes;
		assert indexes2 != null;
		return indexes2.length-2;
	}

	@Override
	public @NonNull FlatModel getFlatModel() {
		return flatModel;
	}

	private @Nullable InheritanceFragment getFragment(@NonNull AbstractFlatClass that) {
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

	private @NonNull InheritanceFragment getFragment(int fragmentNumber) {
		assert fragments != null;
		return fragments[fragmentNumber];
	}

	private int getIndex(int fragmentNumber) {
		int @Nullable [] indexes2 = indexes;
		assert indexes2 != null;
		return indexes2[fragmentNumber];
	}

	@Override
	public @NonNull Property getMemberProperty(@NonNull String propertyName) {
		throw new UnsupportedOperationException();		// XXX Use local cache
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

//	public abstract org.eclipse.ocl.pivot.@NonNull Class getPivotClass();

	@Override
	public @NonNull InheritanceFragment getSelfFragment() {
		if (indexes == null) {
			initFragments();
		}
	/*	@NonNull InheritanceFragment @Nullable [] fragments2 = fragments;
		assert fragments2 != null;
		InheritanceFragment fragment = getFragment(fragments2.length-1);
	//	if (fragment == null) {
	//		throw new IllegalStateException("No self fragment"); //$NON-NLS-1$
	//	}
		return fragment; */
		return getFragment(ClassUtil.nonNullState(fragments).length-1);
	}

	@Override
	public @NonNull StandardLibrary getStandardLibrary() {
		return flatModel.getStandardLibrary();
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

	/**
	 * Initialize the super-fragment hierarchy by reflective analysis.
	 */
	private synchronized void initFragments() {
		System.out.println("initFragments for " + NameUtil.debugSimpleName(this) + " : " + this);
		Map<@NonNull FlatClass, @NonNull Iterable<@NonNull FlatClass>> flatClass2superFlatClasses = new HashMap<>();
		// Detect missing OclAny inheritance
		gatherFragmentlessSuperFlatClasses(flatClass2superFlatClasses);
		System.out.println("initFragments for " + NameUtil.debugSimpleName(this) + " : " + this + " fragmentLess: " + flatClass2superFlatClasses.keySet());
		//		int oldPendingCount = uninstalledInheritances.size();
		@SuppressWarnings("unused") List<@NonNull FlatClass> debugOldUninstalledFlatClasses = new ArrayList<>(flatClass2superFlatClasses.keySet());
		while (!flatClass2superFlatClasses.isEmpty()) {
			List<@NonNull FlatClass> flatClasses = new ArrayList<>(flatClass2superFlatClasses.keySet());
			for (@NonNull FlatClass candidateFlatClass : flatClasses) {
				Iterable<@NonNull FlatClass> candidateSuperFlatClasses = flatClass2superFlatClasses.get(candidateFlatClass);
				assert candidateSuperFlatClasses != null;
				boolean allSuperFlatClassesHaveFragments = true;
				for (@NonNull FlatClass candidateSuperFlatClass : candidateSuperFlatClasses) {
					if (flatClass2superFlatClasses.containsKey(candidateSuperFlatClass)) {
						allSuperFlatClassesHaveFragments = false;
						break;
					}
				}
				if (allSuperFlatClassesHaveFragments) {
					System.out.println("initFragments for " + NameUtil.debugSimpleName(this) + " : " + this + " init: " + NameUtil.debugSimpleName(candidateFlatClass) + " : " + candidateFlatClass);
					((AbstractFlatClass)candidateFlatClass).initFragments(candidateSuperFlatClasses);
					flatClass2superFlatClasses.remove(candidateFlatClass);
				}
			}
			if (flatClasses.size() == flatClass2superFlatClasses.size()) {
				StringBuilder s = new StringBuilder();
				s.append("FlatClass loop for "); //$NON-NLS-1$
				for (@NonNull FlatClass flatClass : flatClass2superFlatClasses.keySet()) {
					s.append("\n  "); //$NON-NLS-1$
					s.append(flatClass);
				}
				throw new IllegalStateException(s.toString());
			}
		}
	}

	/**
	 * Install this FlatClass establishing its superClass tables and registering
	 * it to be notified of any changes.
	 *
	 * @return true if installed, false if some superClass uninstallable
	 */
	private void initFragments(@NonNull Iterable<@NonNull FlatClass> directSuperFlatClasses) {
		assert fragments == null;
		assert indexes == null;
		System.out.println("initFragments " + NameUtil.debugSimpleName(this) + " : " + this + " direct: " + directSuperFlatClasses);
		//
		//	Aggregate the flat-classes per depth for the direct super-flat-classes to determine the
		//	flat-classes per depth for this flata-class.
		//
		List<@NonNull List<@NonNull FlatClass>> depth2superFlatClasses = new ArrayList<>();
		for (@NonNull FlatClass directSuperFlatClass : directSuperFlatClasses) {
			AbstractFlatClass abstractDirectSuperFlatClass = (AbstractFlatClass)directSuperFlatClass;
			final @NonNull InheritanceFragment [] superFragments = abstractDirectSuperFlatClass.fragments;
			final int [] superIndexes = abstractDirectSuperFlatClass.indexes;
			assert superFragments != null;
			assert superIndexes != null;
			final int superDepths = superIndexes.length-1;
			for (int i = 0; i < superDepths; i++) {
				List<@NonNull FlatClass> superFlatClasses;
				if (i >= depth2superFlatClasses.size()) {
					superFlatClasses = new ArrayList<>();
					depth2superFlatClasses.add(superFlatClasses);
				}
				else {
					superFlatClasses = depth2superFlatClasses.get(i);
				}
				final int firstIndex = superIndexes[i];
				final int lastIndex = superIndexes[i+1];
				for (int index = firstIndex; index < lastIndex; index++) {
					InheritanceFragment superFragment = superFragments[index];
					AbstractFlatClass baseFlatClass = (AbstractFlatClass)superFragment.getBaseFlatClass();
					if (!superFlatClasses.contains(baseFlatClass)) {
						superFlatClasses.add(baseFlatClass);
						baseFlatClass.addSubFlatClass(this);
					}
				}
			}
		}
		//
		//	Convert the 'all' List-of-List to the 'fragments' concatenated-Array-of-Array and 'indexes' of each Array.
		//
		int superDepths = depth2superFlatClasses.size();
		int fragmentsSize = 0;
		for (List<@NonNull FlatClass> superFlatClasses : depth2superFlatClasses) {
			fragmentsSize += superFlatClasses.size();
		}
		fragmentsSize++;				// Extra 'OclSelf' entry
	//	assert superDepths > 0;
		@NonNull InheritanceFragment @NonNull [] fragments = new @NonNull InheritanceFragment[fragmentsSize];	// +1 for OclSelf
		int @NonNull [] indexes = new int[superDepths+2];		// +1 for OclSelf, +1 for tail pointer
		int fragmentsIndex = 0;
		int indexesIndex = 0;
		indexes[indexesIndex++] = 0;
		while (indexesIndex <= superDepths) {
			List<@NonNull FlatClass> superFlatClasses = depth2superFlatClasses.get(indexesIndex-1);
			Collections.sort(superFlatClasses, NameUtil.NAMEABLE_COMPARATOR);
			for (@NonNull FlatClass superFlatClass : superFlatClasses) {
				fragments[fragmentsIndex++] = createFragment(superFlatClass);
			}
			indexes[indexesIndex++] = fragmentsIndex;
		}
		indexes[superDepths++] = fragmentsIndex;
		fragments[fragmentsIndex++] = createFragment(this);
		indexes[superDepths++] = fragmentsIndex;
		this.fragments = fragments;
		this.indexes = indexes;
	}

	/**
	 * Install the root OclAny FlatClass.
	 *
	private final void installOclAny() {
		assert fragments == null;
		fragments = new @NonNull InheritanceFragment[] { createFragment(this) };
		indexes = new int[] { 0, 1 };
	} */

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
	 *
	private boolean isInstallable() {
		if (isOclAny()) {
			return true;
		}
		if (fragments != null) {
			//			System.out.println("isInstallable true (already) " + this);
			return true;
		}
		//		DomainInheritance oclAnyInheritance = getOclAnyInheritance();
		for (@NonNull FlatClass superFlatClass : computeDirectSuperFlatClasses()) {
			if (((AbstractFlatClass)superFlatClass).fragments == null) {
				return false;
			}
		}
		return true;
	} */

	public final boolean isOclAny() {
		return (flags & OCL_ANY) != 0;
	}

	@Override
	public boolean isOrdered() {
		return (flags & ORDERED) != 0;
	}

	@Override
	public boolean isSubFlatClassOf(@NonNull FlatClass that) {
		AbstractFlatClass abstractThat = (AbstractFlatClass)that;
		int theseFlags = flags & (OCL_VOID|OCL_INVALID);
		int thoseFlags = abstractThat.flags & (OCL_VOID|OCL_INVALID);
		if ((theseFlags == 0) && (thoseFlags == 0)) {
			return getFragment(abstractThat) != null;
		}
		else {
			return theseFlags >= thoseFlags;
		}
	}

	@Override
	public boolean isSuperFlatClassOf(@NonNull FlatClass that) {
		AbstractFlatClass abstractThat = (AbstractFlatClass)that;
		int theseFlags = flags & (OCL_VOID|OCL_INVALID);
		int thoseFlags = abstractThat.flags & (OCL_VOID|OCL_INVALID);
		if ((theseFlags == 0) && (thoseFlags == 0)) {
			return abstractThat.getFragment(this) != null;
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
	//	assert standardLibrary == getStandardLibrary();
		getDepth();
		FlatClass apparentFlatClass = apparentOperation.getFlatClass(standardLibrary);
		if (apparentFlatClass != null) {
			int apparentDepth = apparentFlatClass.getDepth();
			assert indexes != null;
			int depths = indexes.length-1;
			if (apparentDepth+1 < depths) {				// null and invalid may fail here
				int iMax = getIndex(apparentDepth+1);
				for (int i = getIndex(apparentDepth); i < iMax; i++) {
					InheritanceFragment fragment = getFragment(i);
					if (fragment.getBaseFlatClass() == apparentFlatClass) {
						Operation actualOperation = fragment.getActualOperation(apparentOperation);
						if (standardLibrary != getStandardLibrary()) {
							FlatClass apparentFlatClass1 = apparentOperation.getFlatClass(getStandardLibrary());
							if (apparentFlatClass1 != null) {
								int apparentDepth1 = apparentFlatClass1.getDepth();
								if (apparentDepth1+1 < depths) {				// null and invalid may fail here
									int iMax1 = getIndex(apparentDepth+1);
									for (int i1 = getIndex(apparentDepth); i1 < iMax1; i1++) {
										InheritanceFragment fragment1 = getFragment(i1);
										if (fragment1.getBaseFlatClass() == apparentFlatClass) {
											Operation actualOperation1 = fragment.getActualOperation(apparentOperation);
											assert actualOperation1 == actualOperation;
											return actualOperation;
										}
									}
								}
							}

						}
						return actualOperation;
					}
				}
			}
		}
		return apparentOperation;	// invoke apparent op for null and invalid
	}

	@Override
	public @NonNull LibraryFeature lookupImplementation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		assert standardLibrary == getStandardLibrary();
		getDepth();
		FlatClass apparentFlatClass = apparentOperation.getFlatClass(standardLibrary);
		if (apparentFlatClass != null) {
			assert indexes != null;
			int depths = indexes.length-1;
			int apparentDepth = apparentFlatClass.getDepth();
			if (apparentDepth+1 < depths) {				// null and invalid may fail here
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
		assert standardLibrary == getStandardLibrary();
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

	private void removeSubFlatClass(@NonNull FlatClass subFlatClass) {
		if (subFlatClasses != null) {
			subFlatClasses.remove(subFlatClass);
		}
	}

	@Override
	public void resetFragments() {
		@NonNull InheritanceFragment @Nullable [] fragments2 = fragments;
		boolean isNonNull = fragments2 != null;		// FIXME needed for JDT 4.5, not needed for JDT 4.6M4
		if (isNonNull && (fragments2 != null)) {
			//			System.out.println("Uninstall " + this);
			for (@NonNull InheritanceFragment fragment : fragments2) {
				AbstractFlatClass baseFlatClass = (AbstractFlatClass)fragment.getBaseFlatClass();
				baseFlatClass.removeSubFlatClass(this);
			}
			fragments = null;
			indexes = null;
		}
		if (subFlatClasses != null) {
			Set<@NonNull FlatClass> previousSubFlatClasses = subFlatClasses;
			subFlatClasses = null;
			for (@NonNull FlatClass subFlatClass : previousSubFlatClasses) {
				((CompleteClassInternal)subFlatClass.getCompleteClass()).uninstall();
				subFlatClass.resetFragments();
			}
		}
	}
}
