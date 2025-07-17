/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library.executor;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.ClassImpl;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * JavaType supports the usage of Java Class to define the type of an object.
 */
public class JavaType extends ClassImpl
{
	protected final @NonNull Class<?> javaClass;

	public JavaType(@NonNull Class<?> javaClass) {
	//	super(ClassUtil.requireNonNull(javaClass.getName()), 0);
		this.javaClass = javaClass;
		setName(ClassUtil.requireNonNull(javaClass.getName()));
	}

	@Override
	public @NonNull FlatClass getFlatClass(@NonNull StandardLibrary standardLibrary) {
		if (Comparable.class.isAssignableFrom(javaClass)) {
			return standardLibrary.getOclComparableType().getFlatClass(standardLibrary);
		}
		else {
			return standardLibrary.getOclAnyType().getFlatClass(standardLibrary);
		}
	}

	@Override
	@NonNull
	public TypeId getTypeId() {
		throw new UnsupportedOperationException();
//		return TypeId.OCL_VOID;
	}

	@Override
	public @NonNull Operation lookupActualOperation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		FlatClass flatClass = getFlatClass(standardLibrary);
		return flatClass.lookupActualOperation(standardLibrary, apparentOperation);
	}

	@Override
	@NonNull
	public LibraryFeature lookupImplementation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		FlatClass flatClass = getFlatClass(standardLibrary);
		return flatClass.lookupImplementation(standardLibrary, apparentOperation);
	}

	@Override
	public String toString() {
		return getName();
	}
}
