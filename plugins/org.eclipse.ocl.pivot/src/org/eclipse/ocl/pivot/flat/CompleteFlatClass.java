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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Behavior;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Region;
import org.eclipse.ocl.pivot.State;
import org.eclipse.ocl.pivot.StateMachine;
import org.eclipse.ocl.pivot.Stereotype;
import org.eclipse.ocl.pivot.StereotypeExtender;
import org.eclipse.ocl.pivot.Vertex;
import org.eclipse.ocl.pivot.ids.ParametersId;
import org.eclipse.ocl.pivot.internal.ClassImpl;
import org.eclipse.ocl.pivot.internal.CompleteClassImpl;
import org.eclipse.ocl.pivot.internal.complete.ClassListeners.IClassListener;
import org.eclipse.ocl.pivot.internal.library.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * @since 7.0
 */
public class CompleteFlatClass extends AbstractFlatClass implements IClassListener		// XXX FIXME immutable metamodels
{
	protected final @NonNull CompleteClassImpl completeClass;

	/**
	 * Whether this flat class can evolve. Initally null. Set false by static initFragments from XXXTables.
	 * Set true by reflective initFragments.
	 */
	private @Nullable Boolean mutable = null;			// XXX (mutable != null) == (indexes != null)

	/**
	 * The sub-FlatClasses that have been installed, and which must be
	 * invalidated in the event of an inheritance change for this FlatClass.
	 */
	private @Nullable Set<@NonNull CompleteFlatClass> subFlatClasses = null;

	/**
	 * Lazily created map from state name to the known state.
	 */
	private @Nullable Map<@NonNull String, @NonNull State> name2states = null;	// ??? demote to a UMLFlatClass

	public CompleteFlatClass(@NonNull CompleteFlatModel flatModel, @NonNull CompleteClass completeClass) {
		super(flatModel, PivotUtil.getName(completeClass), computeFlags(completeClass.getPrimaryClass()));
		this.completeClass = (CompleteClassImpl)completeClass;
		this.completeClass.addClassListener(this);
	}

	private void addSubFlatClass(@NonNull CompleteFlatClass subFlatClass) {
		Set<@NonNull CompleteFlatClass> subFlatClasses2 = subFlatClasses;
		if (subFlatClasses2 == null) {
			subFlatClasses = subFlatClasses2 = new HashSet<>();
		}
		subFlatClasses2.add(subFlatClass);
	}

	protected @NonNull Operation @NonNull [] computeDirectOperations() {
		List<@NonNull Operation> asOperations = null;
		for (org.eclipse.ocl.pivot.@NonNull Class partialClass : PivotUtil.getPartialClasses(completeClass)) {
			org.eclipse.ocl.pivot.Class genericType = PivotUtil.getGenericElement(partialClass);
			asOperations = gatherDirectOperations(genericType, asOperations);
		}
		return asOperations != null ? asOperations.toArray(new @NonNull Operation[asOperations.size()]) : NO_OPERATIONS;
	}

	protected @NonNull Property @NonNull [] computeDirectProperties() {
		List<@NonNull Property> asProperties = null;
		for (org.eclipse.ocl.pivot.@NonNull Class partialClass : PivotUtil.getPartialClasses(completeClass)) {
			org.eclipse.ocl.pivot.Class genericType = PivotUtil.getGenericElement(partialClass);
			asProperties = gatherDirectProperties(genericType, asProperties);
		}
		return asProperties != null ? asProperties.toArray(new @NonNull Property[asProperties.size()]) : NO_PROPERTIES;
	}

	@Override
	protected @NonNull Iterable<@NonNull FlatClass> computeDirectSuperFlatClasses() {
		assert !isOclAny();
		List<@NonNull FlatClass> superFlatClasses = null;
		FlatClass flatClass = completeClass.getFlatClass();
		CompleteModel completeModel = completeClass.getCompleteModel();
		CompleteStandardLibrary standardLibrary = completeModel.getStandardLibrary();
		for (org.eclipse.ocl.pivot.@NonNull Class partialClass : PivotUtil.getPartialClasses(completeClass)) {
			for (org.eclipse.ocl.pivot.@NonNull Class partialSuperClass : PivotUtil.getSuperClasses(partialClass)) {			// XXX getGeneric
				if (superFlatClasses == null) {
					superFlatClasses = new ArrayList<>();
				}
				CompleteClass superCompleteClass = completeModel.getCompleteClass(PivotUtil.getGenericElement(partialSuperClass));
				FlatClass superFlatClass = superCompleteClass.getFlatClass();
				if ((superFlatClass != flatClass) && !superFlatClasses.contains(superFlatClass)) {		// (very) small list does not merit any usage of a Set within a UniqueList
					superFlatClasses.add(superFlatClass);
				}
			}
		}
		if (superFlatClasses == null) {
			org.eclipse.ocl.pivot.@NonNull Class oclAnyClass = standardLibrary.getOclAnyType();
			CompleteClass completeOclAnyClass = completeModel.getCompleteClass(oclAnyClass);
			FlatClass oclAnyFlatClass = completeOclAnyClass.getFlatClass();
			superFlatClasses = Collections.singletonList(oclAnyFlatClass);
		}
		return superFlatClasses;
	}

	@Override
	public final void didAddOperation(@NonNull Operation partialOperation) {
		resetOperations();
	}

	@Override
	public void didAddPartialClass(int index, org.eclipse.ocl.pivot.@NonNull Class partialClass) {
		resetFragments();
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
	public void didRemovePartialClass(int index, org.eclipse.ocl.pivot.@NonNull Class partialClass) {
		resetFragments();
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
		assert PivotUtil.getGenericElement(asClass) == asClass;
		for (@NonNull Operation partialOperation : PivotUtil.getOwnedOperations(asClass)) {
			if (asOperations == null) {
				asOperations = new ArrayList<>();
			}
			asOperations.add(partialOperation);	// This is a simple list of ingredients; merge is callers responsibility wrt the name.
		}
		return asOperations;
	}

	protected @Nullable List<@NonNull Property> gatherDirectProperties(org.eclipse.ocl.pivot.@NonNull Class asClass, @Nullable List<@NonNull Property> asProperties) {
		assert PivotUtil.getGenericElement(asClass) == asClass;		// FIXME This is much less than PartialClasses.initMemberProperties
		for (@NonNull Property partialProperty : PivotUtil.getOwnedProperties(asClass)) {
			if (asProperties == null) {
				asProperties = new ArrayList<>();
			}
			asProperties.add(partialProperty);	// This is a simple list of ingredients; merge is callers responsibility wrt the name.
		}
		return asProperties;
	}

	protected @Nullable Set<@NonNull Stereotype> gatherExtendingStereotypes(org.eclipse.ocl.pivot.@NonNull Class asClass, @Nullable Set<@NonNull Stereotype> extendingStereotypes) {
		assert PivotUtil.getGenericElement(asClass) == asClass;		// FIXME This is much than PartialClasses.initMemberProperties
		List<StereotypeExtender> extendedBys = asClass.getExtenders();
		if (extendedBys.size() > 0) {
			if (extendingStereotypes == null) {
				extendingStereotypes = new HashSet<@NonNull Stereotype>();
			}
			for (@NonNull StereotypeExtender typeExtension : ClassUtil.nullFree(extendedBys)) {
				Stereotype stereotype = typeExtension.getOwningStereotype();
				if (stereotype != null) {
					extendingStereotypes.add(stereotype);
				}
			}
		}
		return extendingStereotypes;
	}

	/**
	 * Populate the keys of flatClass2superFlatClasses with FlatClasses (including this FlatClass) within
	 * the super-FlatClass hierarchy that have no fragemnts describing thir super-FlatClasses. The corresponding
	 * values identify the direct super-FlatClasses.
	 */
	private void gatherFragmentlessSuperFlatClasses(@NonNull Map<@NonNull FlatClass, @NonNull Iterable<@NonNull FlatClass>> flatClass2superFlatClasses) {
		if ((basicGetFragments() == null) && !flatClass2superFlatClasses.containsKey(this)) {
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
				((CompleteFlatClass)superFlatClass).gatherFragmentlessSuperFlatClasses(flatClass2superFlatClasses);
			}
		}
	}

	@Override
	public @NonNull CompleteClass getCompleteClass() {
		return completeClass;
	}

	@Override
	protected @Nullable Operation getFragmentOperation(@NonNull FlatFragment flatFragment, @NonNull Operation asOperation) {
		assert this == flatFragment.derivedFlatClass;
		String baseOperationName = asOperation.getName();
		ParametersId baseParametersId = asOperation.getParametersId();
		Operation bestOperation = null;
		for (org.eclipse.ocl.pivot.@NonNull Class partialClass : PivotUtil.getPartialClasses(completeClass)) {
			for (@NonNull Operation memberOperation : PivotUtil.getOwnedOperations(partialClass)) {
				if (memberOperation.getName().equals(baseOperationName) && (memberOperation.getParametersId() == baseParametersId)) {
					if (memberOperation.getESObject() != null) {
						return memberOperation;
					}
					if (bestOperation == null) {
						bestOperation = memberOperation;
					}
					else if ((memberOperation.getBodyExpression() != null) && (bestOperation.getBodyExpression() == null)) {
						bestOperation = memberOperation;
					}
				}
			}
		}
		return bestOperation;					// null if not known locally, caller must try superfragments.
	}

	@Override
	protected @NonNull FlatFragment @NonNull [] getFragments() {
		if (basicGetFragments() == null) {
			initFragments();
		}
		return super.getFragments();
	}

	@Override
	protected @NonNull Operation[] getOperations(@NonNull FlatFragment fragment) {
		@NonNull Operation [] operations = fragment.basicGetOperations();
		if (operations == null) {
			operations = ((CompleteFlatClass) fragment.getBaseFlatClass()).computeDirectOperations();
			fragment.initOperations(operations);
		}
		return operations;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getPivotClass() {
		return completeClass.getPrimaryClass();
	}

	@Override
	protected @NonNull Property[] getProperties(@NonNull FlatFragment fragment) {
		@NonNull Property [] properties = fragment.basicGetProperties();
		if (properties == null) {
			properties = ((CompleteFlatClass) fragment.getBaseFlatClass()).computeDirectProperties();
			fragment.initProperties(properties);
		}
		return properties;
	}

	public @NonNull Iterable<@NonNull State> getStates() {
		Map<@NonNull String, @NonNull State> name2states2 = name2states;
		if (name2states2 == null) {
			name2states2 = initStates();
		}
		return name2states2.values();
	}

	public @NonNull Iterable<@NonNull State> getStates(@Nullable String name) {
		Map<@NonNull String, @NonNull State> name2states2 = name2states;
		if (name2states2 == null) {
			name2states2 = initStates();
		}
		State state = name2states2.get(name);
		if (state == null) {
			return PivotConstants.EMPTY_STATE_LIST;
		}
		else {
			return Collections.singletonList(state);
		}
	}

	/**
	 * Initialize the super-fragment hierarchy by reflective analysis.
	 */
	private synchronized void initFragments() {			// XXX Bypass for PartialFlatClass
		assert mutable != Boolean.FALSE;
		assert this instanceof CompleteFlatClass;
	//	toString();			// XXX
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
	//				System.out.println("initFragments for " + NameUtil.debugSimpleName(this) + " : " + this + " init: " + NameUtil.debugSimpleName(candidateFlatClass) + " : " + candidateFlatClass);
					((CompleteFlatClass)candidateFlatClass).initFragments(candidateSuperFlatClasses);
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
		assert assertValidFragments();
		if (DYNAMIC_FRAGMENTS.isActive()) {
			StringBuilder s = new StringBuilder();
			s.append(NameUtil.debugSimpleName(getStandardLibrary()) + " " + NameUtil.debugSimpleName(this) + " : " + this + " " + Arrays.toString(basicGetIndexes()) + " " + Arrays.toString(basicGetFragments()));
			DYNAMIC_FRAGMENTS.println(s.toString());
		}
	//	System.out.println("initFragments for " + NameUtil.debugSimpleName(this) + " : " + this + " indexes: " + Arrays.toString(indexes) + " : " + Arrays.toString(fragments));
		assert isMutable();
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
		assert basicGetFragments() == null;
		assert basicGetIndexes() == null;
	//	System.out.println("initFragments " + NameUtil.debugSimpleName(this) + " : " + this + " direct: " + directSuperFlatClasses);
		//
		//	Aggregate the flat-classes per depth for the direct super-flat-classes to determine the
		//	flat-classes per depth for this flata-class.
		//
		List<@NonNull List<@NonNull FlatClass>> depth2superFlatClasses = new ArrayList<>();
		for (@NonNull FlatClass directSuperFlatClass : directSuperFlatClasses) {
			AbstractFlatClass abstractDirectSuperFlatClass = (AbstractFlatClass)directSuperFlatClass;
			final @NonNull FlatFragment[] superFragments = abstractDirectSuperFlatClass.getFragments();
			final int [] superIndexes = abstractDirectSuperFlatClass.basicGetIndexes();
			assert superIndexes != null;
			final int superDepths = superIndexes.length;
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
				final int lastIndex = i+1 < superIndexes.length ? superIndexes[i+1] : superIndexes[superIndexes.length-1]+1;
				for (int index = firstIndex; index < lastIndex; index++) {
					FlatFragment superFragment = superFragments[index];
					CompleteFlatClass baseFlatClass = (CompleteFlatClass)superFragment.getBaseFlatClass();
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
		int @NonNull [] indexes = new int[superDepths+1];		// +1 for OclSelf
		int fragmentsIndex = 0;
		int indexesIndex = 0;
		indexes[indexesIndex++] = 0;
		while (indexesIndex <= superDepths) {
			List<@NonNull FlatClass> superFlatClasses = depth2superFlatClasses.get(indexesIndex-1);
			Collections.sort(superFlatClasses, NameUtil.NAMEABLE_COMPARATOR);	// XXX if size > 1
			for (@NonNull FlatClass superFlatClass : superFlatClasses) {
				fragments[fragmentsIndex++] = createFragment(superFlatClass);
			}
			indexes[indexesIndex++] = fragmentsIndex;
		}
	//	indexes[superDepths++] = fragmentsIndex;
		fragments[fragmentsIndex++] = createFragment(this);
	//	indexes[superDepths++] = fragmentsIndex;
		super.initFragments(fragments, indexes);;
	//	this.fragments = fragments;
	//	this.indexes = indexes;
	//	assert assertValidFragments();
		installClassListeners();
	}

	@Override
	public void initFragments(@NonNull FlatFragment @NonNull [] fragments, int @NonNull [] startIndexes) {
		assert this.mutable == null;
		super.initFragments(fragments, startIndexes);;
		this.mutable = Boolean.FALSE;
	//	if (STATIC_FRAGMENTS.isActive()) {
	//		StringBuilder s = new StringBuilder();
	//		s.append(NameUtil.debugSimpleName(getStandardLibrary()) + " " + NameUtil.debugSimpleName(this) + " : " + this + " " + Arrays.toString(indexes) + " " + Arrays.toString(fragments));
	//		STATIC_FRAGMENTS.println(s.toString());
	//	}
	//	assert assertValidFragments();
	}

	protected @NonNull Map<@NonNull String, @NonNull State> initStates() {
		Map<@NonNull String, @NonNull State> name2states = new HashMap<@NonNull String, @NonNull State>();
		for (@NonNull CompleteClass superCompleteClass : completeClass.getSuperCompleteClasses()) {
			for (org.eclipse.ocl.pivot.@NonNull Class superPartialClass : ClassUtil.nullFree(superCompleteClass.getPartialClasses())) {
				for (@NonNull Behavior behavior : ClassUtil.nullFree(superPartialClass.getOwnedBehaviors())) {
					if (behavior instanceof StateMachine) {
						@NonNull List<@NonNull Region> regions = ClassUtil.nullFree(((StateMachine)behavior).getOwnedRegions());
						initStatesForRegions(name2states, regions);
					}
				}
			}
		}
		return name2states;
	}
	protected void initStatesForRegions(@NonNull Map<String, State> name2states, @NonNull List<@NonNull Region> regions) {
		for (@NonNull Region region : regions) {
			for (@NonNull Vertex vertex : ClassUtil.nullFree(region.getOwnedSubvertexes())) {
				if (vertex instanceof State) {
					State state = (State) vertex;
					name2states.put(vertex.getName(), state);
					@NonNull List<@NonNull Region> nestedRegions = ClassUtil.nullFree(state.getOwnedRegions());
					initStatesForRegions(name2states, nestedRegions);
				}
			}
		}
	}

	protected void installClassListeners() {
		assert isMutable();
		for (org.eclipse.ocl.pivot.@NonNull Class partialClass : PivotUtil.getPartialClasses(completeClass)) {
			((ClassImpl)partialClass).addClassListener(this);
		}
		completeClass.addClassListener(this);
	}

	protected final boolean isMutable() {
		assert mutable != null;
		return mutable.booleanValue();
	}

	private void removeSubFlatClass(@NonNull FlatClass subFlatClass) {
		if (subFlatClasses != null) {
			subFlatClasses.remove(subFlatClass);
		}
	}

/*	@Override
	public void resetFragments() {
		completeClass.removeClassListener(this);
		for (org.eclipse.ocl.pivot.@NonNull Class partialClass : PivotUtil.getPartialClasses(completeClass)) {
			((ClassImpl)partialClass).removeClassListener(this);
		}
		completeClass.uninstall();
		super.resetFragments();
	} */

//	@Override
	public void resetFragments() {
		if (mutable == null) {				// 'premature' resetFragments
			assert basicGetFragments() == null;
			assert basicGetName2parametersId2operationOrOperations() == null;
			assert basicGetName2propertyOrProperties() == null;
			return;
		}
		assert isMutable();
		@NonNull FlatFragment @Nullable [] fragments2 = basicGetFragments();
		boolean isNonNull = fragments2 != null;		// FIXME needed for JDT 4.5, not needed for JDT 4.6M4
		if (isNonNull && (fragments2 != null)) {
			//			System.out.println("Uninstall " + this);
			for (@NonNull FlatFragment fragment : fragments2) {
				CompleteFlatClass baseFlatClass = (CompleteFlatClass)fragment.getBaseFlatClass();
				baseFlatClass.removeSubFlatClass(this);
			}
		//	fragments = null;
		//	indexes = null;
			resetFragmentsInternal();
		}
		if (subFlatClasses != null) {
			Set<@NonNull CompleteFlatClass> previousSubFlatClasses = subFlatClasses;
			subFlatClasses = null;
			for (@NonNull CompleteFlatClass subFlatClass : previousSubFlatClasses) {
				subFlatClass.resetFragments();
			}
		}
		resetOperations();
		resetProperties();
	}

	@Override
	public void resetOperations() {
		super.resetOperations();
	}

	@Override
	public void resetProperties() {
		super.resetProperties();
	}

	// XXX resetStates

	@Override
	public @NonNull String toString() {
//		return NameUtil.qualifiedNameFor(completeClass);
//		return completeClass.getPrimaryClass().toString();
		return completeClass.getOwningCompletePackage().getName() + "::" + name;
	}
}
