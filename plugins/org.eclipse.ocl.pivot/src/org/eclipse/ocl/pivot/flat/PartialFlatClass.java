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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * A PartialFlatClass identifies a Pivot Class as the client for which caches are provided.
 * <br>
 * This calls is not yet used by itself since current usage always provides a EClassifier
 * for the more refined EcoreFlatClass.
 * @since 7.0
 */
public class PartialFlatClass extends AbstractFlatClass		// XXX FIXME immutable metamodels
{
	private static @NonNull String getName(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		String name = asClass.getName();
		if (name == null) {			// Shoulfn't really happen for a nameless class e.g. UML Association, but why crash?
			name = "$anon_" + Integer.toHexString(System.identityHashCode(asClass));
		}
		return name;
	}
	protected final org.eclipse.ocl.pivot.@NonNull Class asClass;

	protected PartialFlatClass(@NonNull FlatModel flatModel, org.eclipse.ocl.pivot.@NonNull Class asClass, int flags) {
		super(flatModel, getName(asClass), flags);
		this.asClass = asClass;
		assert PivotUtil.getGenericElement(asClass) == asClass;
	}

	@Override
	public @NonNull CompleteClass getCompleteClass() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected @Nullable Operation getFragmentOperation(@NonNull FlatFragment flatFragment, @NonNull Operation asOperation) {
		assert this == flatFragment.derivedFlatClass;
		int index = asOperation.getIndex();
		if (index >= 0) {
			@NonNull
			Operation[] fragmentOperations = flatFragment.basicGetOperations();
			assert fragmentOperations != null;
			return fragmentOperations[index];
		}
		else {
			return null;
		}
	}

	@Override
	protected @NonNull Operation[] getOperations(@NonNull FlatFragment fragment) {
		return fragment.getOperations();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getPivotClass() {
		return asClass;
	}

	@Override
	protected @NonNull Property[] getProperties(@NonNull FlatFragment fragment) {
		return fragment.getProperties();
	}

	@Override
	public @NonNull String toString() {
		return NameUtil.qualifiedNameFor(asClass);
	}
}
