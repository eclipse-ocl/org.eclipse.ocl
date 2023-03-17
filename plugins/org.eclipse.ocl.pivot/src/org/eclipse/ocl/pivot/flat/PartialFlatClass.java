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
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * A PartialFlatClass identifies a Pivot Class as the client for which caches are provided.
 * <br>
 * This calls is not yet used by itself since current usage always provides a EClassifier
 * for the more refined EcoreFlatClass.
 */
public class PartialFlatClass extends AbstractFlatClass		// XXX FIXME immutable metamodels
{
	protected final org.eclipse.ocl.pivot.@NonNull Class asClass;

	protected PartialFlatClass(@NonNull FlatModel flatModel, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		super(flatModel, NameUtil.getName(asClass), 0);
		this.asClass = asClass;
		assert PivotUtil.getUnspecializedTemplateableElement(asClass) == asClass;
	}

	@Override
	protected @NonNull Operation @NonNull [] computeDirectOperations() {
		List<@NonNull Operation> asOperations = gatherDirectOperations(asClass, null);
		return asOperations != null ? asOperations.toArray(new @NonNull Operation[asOperations.size()]) : NO_OPERATIONS;
	}

	@Override
	protected @NonNull Property @NonNull [] computeDirectProperties() {
		List<@NonNull Property> asProperties = gatherDirectProperties(asClass, null);
		return asProperties != null ? asProperties.toArray(new @NonNull Property[asProperties.size()]) : NO_PROPERTIES;
	}

	@Override
	protected @NonNull Iterable<@NonNull FlatClass> computeDirectSuperFlatClasses() {	// This occurs before AS superclasses are defined
		assert !isOclAny();
		List<@NonNull FlatClass> superFlatClasses = null;
		FlatModel flatModel2 = getFlatModel();
		for (org.eclipse.ocl.pivot.@NonNull Class asSuperClass : PivotUtil.getSuperClasses(asClass)) {
			if (superFlatClasses == null) {
				superFlatClasses = new ArrayList<>();
			}
			FlatClass superFlatClass = flatModel2.getFlatClass(asSuperClass);
			if (!superFlatClasses.contains(superFlatClass)) {		// (very) small list does not merit any usage of a Set within a UniqueList
				superFlatClasses.add(superFlatClass);
			}
		}
		if (superFlatClasses == null) {
			StandardLibrary standardLibrary = getStandardLibrary();
			org.eclipse.ocl.pivot.@NonNull Class oclAnyClass = standardLibrary.getOclAnyType();
			FlatClass oclAnyFlatClass = oclAnyClass.getFlatClass(standardLibrary);
			superFlatClasses = Collections.singletonList(oclAnyFlatClass);
		}
		return superFlatClasses;
	}

	@Override
	public final void didAddPartialClass(int index, org.eclipse.ocl.pivot.@NonNull Class partialClass) {
		// Partial class ignores siblings
	}

	@Override
	public final void didRemovePartialClass(int index, org.eclipse.ocl.pivot.@NonNull Class partialClass) {
		// Partial class ignores siblings
	}

	@Override
	public @NonNull CompleteClass getCompleteClass() {
		throw new UnsupportedOperationException();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getPivotClass() {
		return asClass;
	}

	@Override
	protected void initOperationsInternal() {
		for (org.eclipse.ocl.pivot.@NonNull Class superType : PivotUtil.getSuperClasses(asClass)) {
			org.eclipse.ocl.pivot.Class unspecializedType = PivotUtil.getUnspecializedTemplateableElement(superType);
			//	initMemberOperationsFrom(unspecializedPartialType);
			//	if (INIT_MEMBER_OPERATIONS.isActive()) {
			//		INIT_MEMBER_OPERATIONS.println(this + " from " + unspecializedPartialType);
			//	}
			for (@SuppressWarnings("null")@NonNull Operation pivotOperation : unspecializedType.getOwnedOperations()) {
				if (pivotOperation.getName() != null) {		// name may be null for partially initialized Complete OCL document.
					addOperation(pivotOperation);
				}
			}
		}
	}

	@Override
	protected void installClassListeners() {
		assert isMutable();
		asClass.addClassListener(this);
	}

	@Override
	public void resetFragments() {
		asClass.removeClassListener(this);
		super.resetFragments();
	}

	@Override
	public @NonNull String toString() {
		return NameUtil.qualifiedNameFor(asClass);
	}
}
