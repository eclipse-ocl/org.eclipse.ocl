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
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.internal.CompleteClassImpl;
import org.eclipse.ocl.pivot.internal.complete.CompleteClassInternal;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.internal.executor.CompleteReflectiveFragment;
import org.eclipse.ocl.pivot.types.AbstractFragment;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

public class CompleteFlatClass extends AbstractFlatClass		// XXX FIXME immutable metamodels
{
	protected final @NonNull CompleteClassInternal completeClass;

	public CompleteFlatClass(@NonNull CompleteFlatModel flatModel, @NonNull CompleteClassImpl completeClass) {
		super(flatModel, NameUtil.getName(completeClass), computeFlags(completeClass.getPrimaryClass()));
		this.completeClass = completeClass;
	}

	@Override
	protected @NonNull Iterable<@NonNull FlatClass> computeDirectSuperFlatClasses() {
		assert !isOclAny();
		List<@NonNull FlatClass> superFlatClasses = null;
		StandardLibrary standardLibrary = getStandardLibrary();
		CompleteModelInternal completeModel = completeClass.getCompleteModel();
		for (org.eclipse.ocl.pivot.@NonNull Class partialClass : completeClass.getPartialClasses()) {
			for (org.eclipse.ocl.pivot.@NonNull Class partialSuperClass : PivotUtil.getSuperClasses(partialClass)) {			// XXX getUnspecializedElement
				if (superFlatClasses == null) {
					superFlatClasses = new ArrayList<>();
				}
				CompleteClassInternal superCompleteClass = completeModel.getCompleteClass(PivotUtil.getUnspecializedTemplateableElement(partialSuperClass));
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
	protected @NonNull AbstractFragment createFragment(@NonNull FlatClass baseFlatClass) {
		return new CompleteReflectiveFragment(this, baseFlatClass);
	}

	@Override
	public @NonNull CompleteClass getCompleteClass() {
		return completeClass;
	}

	@Override
	public @NonNull Property getMemberProperty(@NonNull String propertyName) {
		return ClassUtil.nonNullState(completeClass.getProperty(propertyName));		// XXX Use local cache
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getPivotClass() {
		return completeClass.getPrimaryClass();
	}

	@Override
	public @NonNull String toString() {
		return NameUtil.qualifiedNameFor(completeClass);
	}
}
