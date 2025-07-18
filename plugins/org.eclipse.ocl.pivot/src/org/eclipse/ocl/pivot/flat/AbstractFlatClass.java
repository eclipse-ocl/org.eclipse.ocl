/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
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
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.ids.ParametersId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.ClassListeners.IClassListener;
import org.eclipse.ocl.pivot.internal.complete.PartialOperations;
import org.eclipse.ocl.pivot.internal.complete.PartialProperties;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.UnsupportedOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * An AbstractTypeServer provides the co-ordinated operation, property and superclass lookup caches for one or more merged types.
 * @since 7.0
 */
public abstract class AbstractFlatClass implements FlatClass, IClassListener
{
	protected static final @NonNull Operation @NonNull [] NO_OPERATIONS = new @NonNull Operation[0];
	protected static final @NonNull Property @NonNull [] NO_PROPERTIES = new @NonNull Property[0];

	public static int computeFlags(@NonNull Type asType) {
//		assert !(asType instanceof JavaType);
//			return 0;			// XXX Avoid UOE from getTypeId().
//		}
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

	/**
	 * Lazily created map from operation name to map of parameter types to the list of partial operations to be treated as merged.
	 */
	private @Nullable Map<@NonNull String, @NonNull PartialOperations> name2partialOperations = null;

	/**
	 * Cached mapping from a property name to the Property or PartialProperties that has that name within
	 * the FlatClass hierarchy. The PartialProperties holds the ambiguity until a lazy resolution replaces it
	 * by a Property or null
	 */				// XXX Use binary search of array rather than map
	private @Nullable Map<@NonNull String, @Nullable Object> name2propertyOrProperties = null;	// Property or PartialProperties


	/**
	 * Map from invoked operation to its resolved implementation. Theis map is lazily populated with each
	 * enry computed on the first invocation.
	 */
	private @Nullable Map<@NonNull Operation, @NonNull LibraryFeature> operationMap = null;


	/**
	 * Whether this flat class can evolve. Initally null. Set false by static initFragments from XXXTables.
	 * Set true by reflective initFragments.
	 */
	private @Nullable Boolean mutable = null;			// XXX (mutable != null) == (indexes != null)

	/**
	 * Depth ordered inheritance fragments. OclAny at depth 0, OclSelf at depth size-1.
	 */
	private @NonNull FlatFragment @Nullable [] fragments = null;

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
	//	System.out.println("ctor " + NameUtil.debugSimpleName(this) + " : " + name + " " + Integer.toHexString(flags) + " in " + NameUtil.debugSimpleName(flatModel));
	}

	protected void addOperation(@NonNull Operation pivotOperation) {
		Map<String, PartialOperations> name2partialOperations2 = name2partialOperations;
		if (name2partialOperations2 != null) {
			String operationName = pivotOperation.getName();
			if (operationName != null) {
				PartialOperations partialOperations = name2partialOperations2.get(operationName);
				if (partialOperations == null) {
					partialOperations = new PartialOperations(getStandardLibrary(), operationName);
					name2partialOperations2.put(operationName, partialOperations);
				}
				partialOperations.didAddOperation(pivotOperation);
			}
		}
	}

	protected void addProperty(@NonNull Property property) {
		String name = PivotUtil.getName(property);
		Map<@NonNull String, @Nullable Object> name2propertyOrProperties2 = name2propertyOrProperties;
		assert name2propertyOrProperties2 != null;
		Object old = name2propertyOrProperties2.get(name);
		if (old == null) {
			name2propertyOrProperties2.put(name, property);
		}
		else if (old == property) {
			;																	// XXX FIXME should not have inherited legacy duplicates
		}
		else {
			PartialProperties partialProperties;
			if (old instanceof PartialProperties) {
				partialProperties = (PartialProperties)old;
			}
			else {
				partialProperties = new PartialProperties(getStandardLibrary());
				assert name2propertyOrProperties2 != null;
				name2propertyOrProperties2.put(name, partialProperties);
				partialProperties.didAddProperty((Property)old);
			}
			partialProperties.didAddProperty(property);
		}
	}

	private void addSubFlatClass(@NonNull FlatClass subFlatClass) {
		Set<@NonNull FlatClass> subFlatClasses2 = subFlatClasses;
		if (subFlatClasses2 == null) {
			subFlatClasses = subFlatClasses2 = new HashSet<>();
		}
		subFlatClasses2.add(subFlatClass);
	}

	@Override
	public @Nullable Operation basicGetOperation(@NonNull OperationId id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @Nullable Property basicGetProperty(@NonNull String propertyName) {
		if (name2propertyOrProperties == null) {
			if (fragments == null) {
				initFragments();
			}
			initProperties();
		}
		assert name2propertyOrProperties != null;
		Object propertyOrProperties = name2propertyOrProperties.get(propertyName);
		return propertyOrProperties instanceof Property ? (Property)propertyOrProperties : null;
	}

	/**
	 * Return the properties defined for this flat class, which may be need merging for a complete class.
	 * FIXME super flat class properties should not be returned, but are due to legacy static initialization.
	 */
	protected abstract @NonNull Property @NonNull [] computeDirectProperties();

	protected abstract @NonNull Operation @NonNull [] computeDirectOperations();

	/**
	 * Return the immediate super-FlatClasses without reference to the fragments.
	 * This method is never invoked for OclAny, consequently there is always at least one direct super-FlatClass.
	 */
	protected abstract @NonNull Iterable<@NonNull FlatClass> computeDirectSuperFlatClasses();

	protected /* final */@NonNull FlatFragment createFragment(@NonNull FlatClass baseFlatClass) {
		return new FlatFragment(this, baseFlatClass);
	}

	@Override
	public final void didAddOperation(@NonNull Operation partialOperation) {
		resetOperations();
	}

	@Override
	public final void didAddProperty(@NonNull Property partialProperty) {
		resetProperties();
	}

	@Override
	public final void didAddSuperClass(org.eclipse.ocl.pivot.@NonNull Class partialClass) {
		resetFragments();
	}

	@Override
	public final void didRemoveOperation(@NonNull Operation partialOperation) {
		resetOperations();
	}

	@Override
	public final void didRemoveProperty(@NonNull Property partialProperty) {
		resetProperties();
	}

	@Override
	public final void didRemoveSuperClass(org.eclipse.ocl.pivot.@NonNull Class partialClass) {
		resetFragments();
	}

	protected @Nullable List<@NonNull Operation> gatherDirectOperations(org.eclipse.ocl.pivot.@NonNull Class asClass, @Nullable List<@NonNull Operation> asOperations) {
		assert PivotUtil.getUnspecializedTemplateableElement(asClass) == asClass;
		for (@NonNull Operation partialOperation : PivotUtil.getOwnedOperations(asClass)) {
			if (asOperations == null) {
				asOperations = new ArrayList<>();
			}
			asOperations.add(partialOperation);	// This is a simple list of ingredients; merge is callers responsibility wrt the name.
		}
		return asOperations;
	}

	protected @Nullable List<@NonNull Property> gatherDirectProperties(org.eclipse.ocl.pivot.@NonNull Class asClass, @Nullable List<@NonNull Property> asProperties) {
		assert PivotUtil.getUnspecializedTemplateableElement(asClass) == asClass;		// FIXME This is much less than PartialClasses.initMemberProperties
		for (@NonNull Property partialProperty : PivotUtil.getOwnedProperties(asClass)) {
			if (asProperties == null) {
				asProperties = new ArrayList<>();
			}
			asProperties.add(partialProperty);	// This is a simple list of ingredients; merge is callers responsibility wrt the name.
		}
		return asProperties;
	}

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
	 * Return the actualOperation that has the same signature as apparentOperation.
	 */
	private @NonNull Operation getActualOperation(@NonNull FlatFragment flatFragment, @NonNull Operation apparentOperation) {
		assert this == flatFragment.derivedFlatClass;
		Operation localOperation = getFragmentOperation(flatFragment, apparentOperation);
		if (localOperation == null) {
			if (this == flatFragment.baseFlatClass) {
				localOperation = apparentOperation;
			}
		}
		if (localOperation == null) {				// Non-trivial, search up the inheritance tree for an inherited operation
			Operation bestOverload = flatFragment.baseFlatClass.getBestOverload(this, apparentOperation);
			if (bestOverload != null) {
				localOperation = bestOverload;
			}
			else {
				toString();		// XXX
				throw new InvalidValueException(PivotMessages.AmbiguousOperation, apparentOperation, this);
			}
		}
		return localOperation;
	}

	/**
	 * Return a depth ordered, OclAny-first, OclSelf-last, Iterable of all the super-adapters excluding this one.
	 */
	@Override
	public @NonNull FragmentIterable getAllProperSuperFragments() {
		if (fragments == null) {
			initFragments();
		}
		@NonNull FlatFragment @NonNull [] fragments2 = ClassUtil.requireNonNull(fragments);
		return new FragmentIterable(fragments2, 0, fragments2.length-1);
	}

	@Override
	public @NonNull FragmentIterable getAllSuperFragments() {
		if (fragments == null) {
			initFragments();
		}
		return new FragmentIterable(ClassUtil.requireNonNull(fragments));
	}

	@Override
	public @Nullable Operation getBestOverload(@NonNull FlatClass derivedFlatClass, @NonNull Operation apparentOperation) {
		AbstractFlatClass baseFlatClass = this;
		Operation bestOverload = null;
		FlatClass bestFlatClass = null;
		int bestDepth = -1;
		int minDepth = baseFlatClass.getDepth();
		for (int depth = derivedFlatClass.getDepth()-1; depth >= minDepth; depth--) {
			Iterable<@NonNull FlatFragment> derivedSuperFragments = derivedFlatClass.getSuperFragments(depth);
			for (FlatFragment derivedSuperFragment : derivedSuperFragments) {
				AbstractFlatClass superFlatClass = (AbstractFlatClass)derivedSuperFragment.getBaseFlatClass();
				FlatFragment superFragment = superFlatClass.getFragment(baseFlatClass);
				if (superFragment != null) {
					AbstractFlatClass derivedFlatClass2 = (AbstractFlatClass)superFragment.derivedFlatClass;
					Operation overload = derivedFlatClass2.getFragmentOperation(superFragment, apparentOperation);
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

	/*private*/ @Override
	public @Nullable FlatFragment getFragment(@NonNull /*Abstract*/FlatClass that) {
		int staticDepth = that.getDepth();
		if (staticDepth <= getDepth()) {
			int iMax = getIndex(staticDepth+1);
			for (int i = getIndex(staticDepth); i < iMax; i++) {
				FlatFragment fragment = getFragment(i);
				FlatClass baseFlatClass = fragment.getBaseFlatClass();
				if (baseFlatClass == that) {
					return fragment;
				}
			}
		}
		return null;
	}

	/*private*/ @Override
	public @NonNull FlatFragment getFragment(int fragmentNumber) {
		assert fragments != null;
		return fragments[fragmentNumber];
	}

	/**
	 * Return the possibly overloaded declaration of asOperation from flatFragment, or null.
	 */
	protected abstract @Nullable Operation getFragmentOperation(@NonNull FlatFragment flatFragment, @NonNull Operation asOperation);

	private @NonNull LibraryFeature getImplementation(@NonNull FlatFragment flatFragment, @NonNull Operation apparentOperation) {
		int index = apparentOperation.getIndex();
		assert index < 0;
		Map<@NonNull Operation, @NonNull LibraryFeature> operationMap2 = operationMap;
		if (operationMap2 == null) {
			synchronized (this) {
				operationMap2 = operationMap;
				if (operationMap2 == null) {
					operationMap = operationMap2 = new HashMap<>();		// Optimize to reuse single super map if no local ops
				}
			}
		}
		LibraryFeature libraryFeature = operationMap2.get(apparentOperation);
		if (libraryFeature != null) {
			return libraryFeature;
		}
		synchronized (operationMap2) {
			libraryFeature = operationMap2.get(apparentOperation);
			if (libraryFeature != null) {
				return libraryFeature;
			}
			AbstractFlatClass derivedFlatClass = (AbstractFlatClass)flatFragment.derivedFlatClass;
			Operation localOperation = derivedFlatClass.getFragmentOperation(flatFragment, apparentOperation);
			if (localOperation == null) {
				if (derivedFlatClass == flatFragment.baseFlatClass) {
					localOperation = apparentOperation;
				}
			}
			if (localOperation != null) {				// Trivial case, there is a local operation
				libraryFeature = PivotUtil.getImplementation(localOperation);
			}
			else {										// Non-trivial, search up the inheritance tree for an inherited operation
				Operation bestOverload = flatFragment.baseFlatClass.getBestOverload(derivedFlatClass, apparentOperation);
				if (bestOverload != null) {
					libraryFeature = PivotUtil.getImplementation(bestOverload);
				}
				else {
					libraryFeature = OclAnyUnsupportedOperation.AMBIGUOUS;
				}
			}
			if (libraryFeature == null) {
				libraryFeature = OclAnyUnsupportedOperation.INSTANCE;
			}
			operationMap2.put(apparentOperation, libraryFeature);
			return libraryFeature;
		}
	}

	/*private*/ @Override
	public int getIndex(int fragmentNumber) {
		int @Nullable [] indexes2 = indexes;
		assert indexes2 != null;
		return indexes2[fragmentNumber];
	}

	/*private*/ @Override
	public int getIndexes() {
		return indexes.length;
	}

//	@Override
//	public @NonNull Iterable<@NonNull ? extends FlatClass> getInitialSuperInheritances() {
//		return isOclAny() ? EMPTY_LIST : completeClass.getPartialClasses().getInitialSuperInheritances();
//	}

//	public @NonNull List<? extends Operation> getLocalOperations() {
//		return ClassUtil.requireNonNull(completeClass.getPrimaryClass().getOwnedOperations());			// FIXME Use local cache
//	}

//	public @NonNull List<? extends Property> getLocalProperties() {
//		return ClassUtil.requireNonNull(completeClass.getPrimaryClass().getOwnedProperties());			// FIXME Use local cache
//	}

	/**
	 * @since 7.0
	 */
//	@Override
//	public @NonNull String getMetaclassName() {
//		return completeClass.getPrimaryClass().getMetaclassName();
//	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	@Override
	public @Nullable Operation getOperation(@NonNull OperationId operationId) {
		Map<String, PartialOperations> name2partialOperations2 = name2partialOperations;
		if (name2partialOperations2 == null) {
			name2partialOperations2 = initOperations();
		}
		String operationName = operationId.getName();
		PartialOperations partialOperations = name2partialOperations2.get(operationName);
		if (partialOperations == null) {
			return null;
		}
		return partialOperations.getOperation(operationId.getParametersId(), null);
	}

//	@Override
//	public @NonNull List<Property> getOwnedProperties() {
//		return ClassUtil.requireNonNull(completeClass.getPrimaryClass().getOwnedProperties());			// FIXME Use local cache
//	}

//	@Override
//	public @NonNull List<Operation> getOwnedOperations() {
//		return ClassUtil.requireNonNull(completeClass.getPrimaryClass().getOwnedOperations());			// FIXME Use local cache
//	}

//	@Override
//	public org.eclipse.ocl.pivot.@NonNull Class getPivotClass() {
//		return getCompleteClass().getPrimaryClass();
//	}

	@Override
	public @NonNull FlatFragment getSelfFragment() {
		if (indexes == null) {
			initFragments();
		}
	/*	@NonNull FlatFragment @Nullable [] fragments2 = fragments;
		assert fragments2 != null;
		FlatFragment fragment = getFragment(fragments2.length-1);
	//	if (fragment == null) {
	//		throw new IllegalStateException("No self fragment"); //$NON-NLS-1$
	//	}
		return fragment; */
		return getFragment(ClassUtil.requireNonNull(fragments).length-1);
	}


	@Override
	public @NonNull StandardLibrary getStandardLibrary() {
		return flatModel.getStandardLibrary();
	}

//	@Override
//	public @NonNull List<org.eclipse.ocl.pivot.Class> getSuperClasses() {
//		List<org.eclipse.ocl.pivot.Class> superClasses = new ArrayList<org.eclipse.ocl.pivot.Class>();
//		for (org.eclipse.ocl.pivot.Class superClass : completeClass.getProperSuperClasses()) {
//			superClasses.add(superClass);
//		}
//		return superClasses;
//	}

	@Override
	public final @NonNull FragmentIterable getSuperFragments(int depth) {
		return new FragmentIterable(ClassUtil.requireNonNull(fragments), indexes[depth], indexes[depth+1]);
	}

//	@Override
//	public final @NonNull TypeId getTypeId() {
//		return completeClass.getPrimaryClass().getTypeId();
//	}

	/**
	 * @since 7.0
	 */
//	@Override
//	public @NonNull TemplateParameters getTemplateParameters() {
//		return TemplateParameters.EMPTY_LIST;
//	}

	/**
	 * Initialize the super-fragment hierarchy by reflective analysis.
	 */
	private synchronized void initFragments() {
		assert mutable != Boolean.FALSE;
	//	this.mutable = Boolean.TRUE;
	//	System.out.println("initFragments for " + NameUtil.debugSimpleName(this) + " : " + this);
		Map<@NonNull FlatClass, @NonNull Iterable<@NonNull FlatClass>> flatClass2superFlatClasses = new HashMap<>();
		// Detect missing OclAny inheritance
		gatherFragmentlessSuperFlatClasses(flatClass2superFlatClasses);
	//	System.out.println("initFragments for " + NameUtil.debugSimpleName(this) + " : " + this + " fragmentLess: " + flatClass2superFlatClasses.keySet());
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
		//			System.out.println("initFragments for " + NameUtil.debugSimpleName(this) + " : " + this + " init: " + NameUtil.debugSimpleName(candidateFlatClass) + " : " + candidateFlatClass);
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
	//	System.out.println("initFragments for " + NameUtil.debugSimpleName(this) + " : " + this + " indexes: " + Arrays.toString(indexes) + " : " + Arrays.toString(fragments));
		assert mutable == Boolean.TRUE;
	}

	@Override
	public void initFragments(@NonNull FlatFragment @NonNull [] fragments, int @NonNull [] depthCounts) {
		assert this.mutable == null;
		assert this.fragments == null;
		assert this.indexes == null;
		int[] indexes = new int[depthCounts.length+1];
		indexes[0] = 0;
		for (int i = 0; i <  depthCounts.length; i++) {
			indexes[i+1] = indexes[i] + depthCounts[i];
		}
		this.fragments = fragments;
		this.indexes = indexes;
		this.mutable = Boolean.FALSE;
	}

	/**
	 * Install this FlatClass establishing its superClass tables and registering
	 * it to be notified of any changes.
	 *
	 * @return true if installed, false if some superClass uninstallable
	 */
	private void initFragments(@NonNull Iterable<@NonNull FlatClass> directSuperFlatClasses) {
		assert mutable != Boolean.FALSE;
		mutable = Boolean.TRUE;
		assert fragments == null;
		assert indexes == null;
	//	System.out.println("initFragments " + NameUtil.debugSimpleName(this) + " : " + this + " direct: " + directSuperFlatClasses);
		//
		//	Aggregate the flat-classes per depth for the direct super-flat-classes to determine the
		//	flat-classes per depth for this flata-class.
		//
		List<@NonNull List<@NonNull FlatClass>> depth2superFlatClasses = new ArrayList<>();
		for (@NonNull FlatClass directSuperFlatClass : directSuperFlatClasses) {
			AbstractFlatClass abstractDirectSuperFlatClass = (AbstractFlatClass)directSuperFlatClass;
			final @NonNull FlatFragment [] superFragments = abstractDirectSuperFlatClass.fragments;
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
					FlatFragment superFragment = superFragments[index];
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
		@NonNull FlatFragment @NonNull [] fragments = new @NonNull FlatFragment[fragmentsSize];	// +1 for OclSelf
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
		installClassListeners();
	}

	private @NonNull Map<@NonNull String, @NonNull PartialOperations> initOperations() {
		Map<@NonNull String, @NonNull PartialOperations> name2partialOperations2 = name2partialOperations;
		if (name2partialOperations2 == null) {
			name2partialOperations2 = name2partialOperations = new HashMap<@NonNull String, @NonNull PartialOperations>();
//			Set<CompleteClass> allSuperCompleteClasses = new HashSet<CompleteClass>();
//			allSuperCompleteClasses.add(completeClass);
//			for (CompleteClass superCompleteClass : completeClass.getSuperCompleteClasses()) {
//				allSuperCompleteClasses.add(superCompleteClass);
//			}
			initOperationsInternal();
		//	for (PartialOperations partialOperations : name2partialOperations2.values()) {
		//		partialOperations.initMemberOperationsPostProcess();
		//	}
		}
		return name2partialOperations2;
	}

	protected abstract void initOperationsInternal();

	private synchronized void initProperties() {
		Map<@NonNull String, @Nullable Object> name2propertyOrProperties2 = name2propertyOrProperties;
		if (name2propertyOrProperties2 == null) {
			name2propertyOrProperties = name2propertyOrProperties2 = new HashMap<>();
			assert fragments != null;
			for (@NonNull FlatFragment fragment : fragments) {
				for (@NonNull Property property : fragment.getProperties()) {
					addProperty(property);
				}
			}
		}
	}

	protected abstract void installClassListeners();

	@Override
	public boolean isAbstract() {
		return (flags & ABSTRACT) != 0;
	}

	@Override
	public final boolean isInvalid() {
		return (flags & OCL_INVALID) != 0;
	}

	protected final boolean isMutable() {
		assert mutable != null;
		return mutable.booleanValue();
	}

	@Override
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
		getDepth();
		FlatClass apparentFlatClass = apparentOperation.getFlatClass(standardLibrary);
		if (apparentFlatClass != null) {
			int apparentDepth = apparentFlatClass.getDepth();
			assert indexes != null;
			int depths = indexes.length-1;
			if (apparentDepth+1 < depths) {				// null and invalid may fail here
				int iMax = getIndex(apparentDepth+1);
				for (int i = getIndex(apparentDepth); i < iMax; i++) {
					FlatFragment fragment = getFragment(i);
					if (fragment.getBaseFlatClass() == apparentFlatClass) {
						Operation actualOperation = getActualOperation(fragment, apparentOperation);
					/*	if (standardLibrary != getStandardLibrary()) {
							FlatClass apparentFlatClass1 = apparentOperation.getFlatClass(getStandardLibrary());
							if (apparentFlatClass1 != null) {
								int apparentDepth1 = apparentFlatClass1.getDepth();
								if (apparentDepth1+1 < depths) {				// null and invalid may fail here
									int iMax1 = getIndex(apparentDepth+1);
									for (int i1 = getIndex(apparentDepth); i1 < iMax1; i1++) {
										FlatFragment fragment1 = getFragment(i1);
										if (fragment1.getBaseFlatClass() == apparentFlatClass) {
											Operation actualOperation1 = getActualOperation(fragment, apparentOperation);
											assert actualOperation1 == actualOperation;
											return actualOperation;
										}
									}
								}
							}
						} */
						return actualOperation;
					}
				}
			}
		}
		return apparentOperation;	// invoke apparent op for null and invalid
	}

	@Override
	public @NonNull LibraryFeature lookupImplementation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		FlatClass apparentFlatClass = apparentOperation.getFlatClass(standardLibrary);		// Base of the operation hierarchy
		if (apparentFlatClass != null) {
			if (indexes == null) {
				initFragments();
			}
			assert indexes != null;
			int depths = indexes.length-1;
			int apparentDepth = apparentFlatClass.getDepth();
			if (apparentDepth+1 < depths) {				// null and invalid may fail here
				int iMax = getIndex(apparentDepth+1);
				for (int i = getIndex(apparentDepth); i < iMax; i++) {
					FlatFragment fragment = getFragment(i);
					if (fragment.getBaseFlatClass() == apparentFlatClass) {
						int index = apparentOperation.getIndex();
						if (index >= 0) {
							@NonNull Operation[] fragmentOperations = fragment.basicGetOperations();
							assert fragmentOperations != null;
							return ClassUtil.requireNonNull(fragmentOperations[index].getImplementation());
						}
						return getImplementation(fragment, apparentOperation);
					}
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

	private void removeSubFlatClass(@NonNull FlatClass subFlatClass) {
		if (subFlatClasses != null) {
			subFlatClasses.remove(subFlatClass);
		}
	}

	@Override
	public void resetFragments() {
		if (mutable == null) {				// 'premature' resetFragments
			assert fragments == null;
			assert name2partialOperations == null;
			assert name2propertyOrProperties == null;
			return;
		}
		assert isMutable();
		@NonNull FlatFragment @Nullable [] fragments2 = fragments;
		boolean isNonNull = fragments2 != null;		// FIXME needed for JDT 4.5, not needed for JDT 4.6M4
		if (isNonNull && (fragments2 != null)) {
			//			System.out.println("Uninstall " + this);
			for (@NonNull FlatFragment fragment : fragments2) {
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
				subFlatClass.resetFragments();
			}
		}
		resetOperations();
		resetProperties();
	}

	public void resetOperations() {
		if (name2partialOperations != null) {
			name2partialOperations.clear();
			name2partialOperations = null;
		}
	}

	public void resetProperties() {
		name2propertyOrProperties = null;
	}


//	@Override
//	public String toString() {
//		return completeClass.getPrimaryClass().toString();
//	}

//	@Override
//	public void uninstall() {
//		System.out.println("uninstall " + toString());
//		completeClass.uninstall();
//		super.uninstall();
//	}
}
