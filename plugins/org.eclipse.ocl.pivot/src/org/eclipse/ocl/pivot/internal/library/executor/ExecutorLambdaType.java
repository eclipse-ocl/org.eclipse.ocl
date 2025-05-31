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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.elements.AbstractExecutorClass;
import org.eclipse.ocl.pivot.library.LibraryFeature;

public class ExecutorLambdaType extends AbstractExecutorClass implements ExecutorTypeArgument
{
//	protected final @NonNull TypeId typeId;
//	protected final @NonNull String name;
	/**
	 * @since 7.0
	 */
	protected /*final @NonNull*/ ExecutorLambdaParameter context;
	/**
	 * @since 7.0
	 */
	protected /*final @NonNull*/ ExecutorLambdaParameter /*@NonNull*/ [] parameters;
	/**
	 * @since 7.0
	 */
	protected /*final @NonNull*/ ExecutorLambdaParameter result;

	/**
	 * @since 7.0
	 */
	public ExecutorLambdaType(@NonNull String name, @NonNull ExecutorLambdaParameter context, @NonNull ExecutorLambdaParameter result, @NonNull ExecutorLambdaParameter @NonNull ... parameters) {
		super(name, 0);
//		typeId = TypeId.BOOLEAN; // XXX IdManager.getLambdaTypeId(name, typeArguments);
//		this.name = name;
		this.context = context;
		this.parameters = parameters;
		this.result = result;
	}

	/**
	 * @since 7.0
	 */
	@Deprecated
	public ExecutorLambdaType(String name, @NonNull ExecutorTypeParameter $$0) {
		super(name, 0);
		this.context = null;
		this.parameters = null;
		this.result = null;
	}

	@Override
	public boolean conformsTo(@NonNull StandardLibrary standardLibrary, @NonNull Type type) {
		throw new UnsupportedOperationException();			// WIP fixme
	}

	@Override
	public @NonNull Type getCommonType(@NonNull IdResolver idResolver, @NonNull Type type) {
		throw new UnsupportedOperationException();			// WIP fixme
	}

	@Override
	public boolean isEqualTo(@NonNull StandardLibrary standardLibrary, @NonNull Type type) {
		throw new UnsupportedOperationException();			// WIP fixme
	}

	@Override
	public @NonNull Operation lookupActualOperation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		throw new UnsupportedOperationException();			// WIP fixme
	}

	@Override
	public @NonNull LibraryFeature lookupImplementation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		throw new UnsupportedOperationException();			// WIP fixme
	}

	@Override
	public @NonNull TypeId getTypeId() {
//		return typeId;
//		throw new UnsupportedOperationException();			// WIP fixme
		return TypeId.BOOLEAN;		// XXX
	}
}