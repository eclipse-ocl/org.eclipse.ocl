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
package org.eclipse.ocl.pivot.flat;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.library.LibraryFeature;

/**
 * An InheritanceFragment identifies the capabilities introduced by a particular inheritance.
 */
public interface InheritanceFragment
{
	/**
	 * Return the unoverloaded fragment, which is getBaseInheritance().getSelfFragment().
	 */
	@NonNull InheritanceFragment getBaseFragment();

	/**
	 * Return the FlatClass that introduces the operations and properties in this fragment.
	 */
	@NonNull FlatClass getBaseFlatClass();

	/**
	 * Return the FlatClass that overloads the operations and properties in this fragment.
	 */
	@NonNull FlatClass getDerivedFlatClass();

	/**
	 * Return the actualOperation that has the same signature as apparentOperation.
	 */
	@Deprecated /* @deprecated no longer used */
	@NonNull Operation getActualOperation(@NonNull Operation apparentOperation);

	/**
	 * Return the implementation of the actualOperation within this fragment that has the same signature as apparentOperation.
	 * If there is no local overload, returns an inherited operation if unambiguous or OclAnyUnsupportedOperation.AMBIGUOUS
	 * if ambiguous.
	 */
	@Deprecated /* @deprecated no longer used */
	@NonNull LibraryFeature getImplementation(@NonNull Operation apparentOperation);

	/**
	 * Return the operation within this fragment that has the same signature as apparentOperation. Returns null if none.
	 */
	@Deprecated /* @deprecated no longer used */
	@Nullable Operation getLocalOperation(@NonNull Operation apparentOperation);

	/**
	 * Return the operations within this fragment in operation index order.
	 */
	@NonNull Iterable<@NonNull Operation> getLocalOperations();

	/**
	 * Return the properties within this fragment in property index order.
	 *
	@NonNull Property @NonNull [] getLocalProperties(); */
}