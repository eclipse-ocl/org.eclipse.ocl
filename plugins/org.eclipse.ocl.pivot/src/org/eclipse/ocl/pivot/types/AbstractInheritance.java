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
package org.eclipse.ocl.pivot.types;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.internal.elements.AbstractExecutorNamedElement;
import org.eclipse.ocl.pivot.library.LibraryFeature;

public abstract class AbstractInheritance extends AbstractExecutorNamedElement implements CompleteInheritance
{
	public static final int ORDERED = FlatClass.ORDERED;
	public static final int UNIQUE = FlatClass.UNIQUE;
	public static final int OCL_ANY = FlatClass.OCL_ANY;
	public static final int OCL_VOID = FlatClass.OCL_VOID;
	public static final int OCL_INVALID = FlatClass.OCL_INVALID;
	/**
	 * @since 1.1
	 */
	public static final int ABSTRACT = FlatClass.ABSTRACT;

	/**
	 * A simple public static method that may be used to force class initialization.
	 */
	public static void initStatics() {}

	protected /*final*/ /*@NonNull*/ FlatClass flatClass = null;

	public AbstractInheritance(@NonNull String name, int flags) {
		super(name);
		if ("OclSelf".equals(name)) {
			getClass();		// XXX
		}
	}

	@Override
	public @NonNull FlatClass getFlatClass() {
		assert flatClass != null;
		return flatClass;
	}

	public @NonNull FlatClass getFlatClass(@NonNull StandardLibrary standardLibrary) {
		assert flatClass != null;
		return flatClass;
	}

	public @NonNull Operation lookupActualOperation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		return flatClass.lookupActualOperation(standardLibrary, apparentOperation);
	}

	public @NonNull LibraryFeature lookupImplementation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		return flatClass.lookupImplementation(standardLibrary, apparentOperation);
	}

	public void setFlatClass(@NonNull FlatClass flatClass) {
		this.flatClass = flatClass;
	}
}
