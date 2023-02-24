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
package org.eclipse.ocl.pivot.internal.library.executor;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.InheritanceFragment;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.elements.AbstractExecutorClass;
import org.eclipse.ocl.pivot.types.FlatClass;

/**
 * A ReflectiveType defines a Type using a compact representation suitable for efficient
 * execution. The representation is derived reflectively from a less efficient representation.
 */
public abstract class ReflectiveInheritance extends AbstractExecutorClass
{
	protected static int computeFlags(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		int flags = 0;
		if (asClass instanceof CollectionType) {
			CollectionType collectionType = (CollectionType)asClass;
			if (collectionType.isOrdered()) {
				flags |= ORDERED;
			}
			if (collectionType.isUnique()) {
				flags |= UNIQUE;
			}
		}
		TypeId typeId = asClass.getTypeId();
		if (typeId == TypeId.OCL_ANY){
			flags |= OCL_ANY;
		}
		else if (typeId == TypeId.OCL_VOID){
			flags |= OCL_VOID;
		}
		else if (typeId == TypeId.OCL_INVALID){
			flags |= OCL_INVALID;
		}
		if (asClass.isIsAbstract()) {
			flags |= ABSTRACT;
		}
		return flags;
	}

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
	private int @Nullable [] indexes = null;

	public ReflectiveInheritance(@NonNull String name, int flags, ExecutorTypeParameter... typeParameters) {
		super(name, flags);
	}

//	protected final @NonNull AbstractFragment createFragment(@NonNull CompleteInheritance baseInheritance) {
//		return createFragment(baseInheritance.getFlatClass());
//	}

	@Override
	public @NonNull EObject createInstance() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @Nullable Object createInstance( @NonNull String value) {
		throw new UnsupportedOperationException();
	}

	public void uninstall() {
		@NonNull InheritanceFragment @Nullable [] fragments2 = fragments;
		boolean isNonNull = fragments2 != null;		// FIXME needed for JDT 4.5, not needed for JDT 4.6M4
		if (isNonNull && (fragments2 != null)) {
			//			System.out.println("Uninstall " + this);
			for (InheritanceFragment fragment : fragments2) {
				FlatClass baseInheritance = fragment.getBaseFlatClass();
				baseInheritance.removeSubInheritance(this.getFlatClass());
			}
			fragments = null;
			indexes = null;
			flatClass.uninstall();
		}
	}
}
