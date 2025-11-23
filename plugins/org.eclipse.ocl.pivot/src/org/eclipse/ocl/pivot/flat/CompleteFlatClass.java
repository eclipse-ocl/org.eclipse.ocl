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
import org.eclipse.ocl.pivot.Behavior;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
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
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * @since 7.0
 */
public class CompleteFlatClass extends AbstractFlatClass		// XXX FIXME immutable metamodels
{
	protected final @NonNull CompleteClassImpl completeClass;

	/**
	 * Lazily created map from state name to the known state.
	 */
	private @Nullable Map<@NonNull String, @NonNull State> name2states = null;	// ??? demote to a UMLFlatClass

	public CompleteFlatClass(@NonNull CompleteFlatModel flatModel, @NonNull CompleteClass completeClass) {
		super(flatModel, PivotUtil.getName(completeClass), computeFlags(completeClass.getPrimaryClass()));
		this.completeClass = (CompleteClassImpl)completeClass;
		this.completeClass.addClassListener(this);
	}

	@Override
	protected @NonNull Operation @NonNull [] computeDirectOperations() {
		List<@NonNull Operation> asOperations = null;
		for (org.eclipse.ocl.pivot.@NonNull Class partialClass : PivotUtil.getPartialClasses(completeClass)) {
			org.eclipse.ocl.pivot.Class genericType = PivotUtil.getGenericElement(partialClass);
			asOperations = gatherDirectOperations(genericType, asOperations);
		}
		return asOperations != null ? asOperations.toArray(new @NonNull Operation[asOperations.size()]) : NO_OPERATIONS;
	}

	@Override
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
		CompleteModel completeModel = completeClass.getCompleteModel();
		CompleteStandardLibrary standardLibrary = completeModel.getStandardLibrary();
		for (org.eclipse.ocl.pivot.@NonNull Class partialClass : PivotUtil.getPartialClasses(completeClass)) {
			for (org.eclipse.ocl.pivot.@NonNull Class partialSuperClass : PivotUtil.getSuperClasses(partialClass)) {			// XXX getGeneric
				if (superFlatClasses == null) {
					superFlatClasses = new ArrayList<>();
				}
				CompleteClass superCompleteClass = completeModel.getCompleteClass(PivotUtil.getGenericElement(partialSuperClass));
				FlatClass superFlatClass = superCompleteClass.getFlatClass();
				if (!superFlatClasses.contains(superFlatClass)) {		// (very) small list does not merit any usage of a Set within a UniqueList
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
	public void didAddPartialClass(int index, org.eclipse.ocl.pivot.@NonNull Class partialClass) {
		resetFragments();
	}

	@Override
	public void didRemovePartialClass(int index, org.eclipse.ocl.pivot.@NonNull Class partialClass) {
		resetFragments();
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
	public org.eclipse.ocl.pivot.@NonNull Class getPivotClass() {
		return completeClass.getPrimaryClass();
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

	@Override
	protected void installClassListeners() {
		assert isMutable();
		for (org.eclipse.ocl.pivot.@NonNull Class partialClass : PivotUtil.getPartialClasses(completeClass)) {
			((ClassImpl)partialClass).addClassListener(this);
		}
		completeClass.addClassListener(this);
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

	// XXX resetStates

	@Override
	public @NonNull String toString() {
//		return NameUtil.qualifiedNameFor(completeClass);
//		return completeClass.getPrimaryClass().toString();
		return completeClass.getOwningCompletePackage().getName() + "::" + name;
	}
}
