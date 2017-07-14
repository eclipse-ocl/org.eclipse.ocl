/*******************************************************************************
 * Copyright (c) 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.iterators;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.values.OCLValue;

/**
 * EqualsStrategy defines the strategy by which two LazyCollectionValue elements are considered equal.
 *
 * Wherever possible, e.g. for data types, the SimpleEqualsStrategy should be used since it just compares 'addresses'.
 * However sometimes. e.g. for Strings, the JavaEqualsStrategy is needed to invoke Object.equals.
 * And for numbers and types, the OCLEqualsStrategy is needed to compare semantic values.
 *
 * @since 1.3
 */
public interface EqualsStrategy
{
	public static abstract class AbstractEqualsStrategy implements EqualsStrategy
	{
		@Override
		public String toString() {
			return getClass().getSimpleName();
		}
	}

	public static class JavaEqualsStrategy extends AbstractEqualsStrategy
	{
		public static final @NonNull JavaEqualsStrategy INSTANCE = new JavaEqualsStrategy();

		@Override
		public boolean isEqual(@Nullable Object firstObject, @Nullable Object secondObject) {
			if (firstObject == null) {
				return secondObject == null;
			}
			else {
				return firstObject.equals(secondObject);
			}
		}
	}

	public static class NotEqualsStrategy extends AbstractEqualsStrategy
	{
		public static final @NonNull NotEqualsStrategy INSTANCE = new NotEqualsStrategy();

		@Override
		public boolean isEqual(@Nullable Object firstObject, @Nullable Object secondObject) {
			return false;
		}
	}

	public static class OCLEqualsStrategy extends AbstractEqualsStrategy
	{
		public static final @NonNull OCLEqualsStrategy INSTANCE = new OCLEqualsStrategy();

		@Override
		public boolean isEqual(@Nullable Object firstObject, @Nullable Object secondObject) {
			if (firstObject == null) {
				return secondObject == null;
			}
			else if (secondObject == null) {
				return false;
			}
			else {
				return ((OCLValue)firstObject).oclEquals((OCLValue)secondObject);
			}
		}
	}

	public static class SimpleEqualsStrategy extends AbstractEqualsStrategy
	{
		public static final @NonNull SimpleEqualsStrategy INSTANCE = new SimpleEqualsStrategy();

		@Override
		public boolean isEqual(@Nullable Object firstObject, @Nullable Object secondObject) {
			return firstObject == secondObject;
		}
	}

	boolean isEqual(@Nullable Object firstObject, @Nullable Object secondObject);
}