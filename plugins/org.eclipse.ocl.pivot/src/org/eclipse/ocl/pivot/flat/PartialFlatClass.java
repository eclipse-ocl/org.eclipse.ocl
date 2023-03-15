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

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.internal.ClassImpl;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

public abstract class PartialFlatClass extends AbstractFlatClass		// XXX FIXME immutable metamodels
{
	protected final org.eclipse.ocl.pivot.@NonNull Class asClass;

	protected PartialFlatClass(@NonNull FlatModel flatModel, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		super(flatModel, NameUtil.getName(asClass), 0);
		this.asClass = asClass;
	//	((ClassImpl)asClass).addClassListener(this);
	}

	@Override
	protected @Nullable List<@NonNull Property> computeDirectProperties() {
		org.eclipse.ocl.pivot.Class unspecializedType = PivotUtil.getUnspecializedTemplateableElement(asClass);
		return gatherDirectProperties(unspecializedType, null);
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
	protected @NonNull EnvironmentFactoryInternal getEnvironmentFactory() {
		throw new UnsupportedOperationException();				// XXX
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
		((ClassImpl)asClass).addClassListener(this);
	}

	@Override
	public void resetFragments() {
		((ClassImpl)asClass).removeClassListener(this);
		super.resetFragments();
	}
}
