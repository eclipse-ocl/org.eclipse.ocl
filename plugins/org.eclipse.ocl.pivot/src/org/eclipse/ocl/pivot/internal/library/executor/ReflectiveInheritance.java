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
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.elements.AbstractExecutorClass;

/**
 * A ReflectiveType defines a Type using a compact representation suitable for efficient
 * execution. The representation is derived reflectively from a less efficient representation.
 */
public abstract class ReflectiveInheritance extends AbstractExecutorClass
{
	public static int computeFlags(org.eclipse.ocl.pivot.@NonNull Class asClass) {
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

	public ReflectiveInheritance(@NonNull String name, int flags, ExecutorTypeParameter... typeParameters) {
		super(name, flags);
	}

	protected ReflectiveInheritance(@NonNull FlatClass flatClass) {
		super(flatClass);
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
		flatClass.uninstall();
	}
}
