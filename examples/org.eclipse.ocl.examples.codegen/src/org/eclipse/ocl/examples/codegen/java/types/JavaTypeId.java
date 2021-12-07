/*******************************************************************************
 * Copyright (c) 2013, 2018 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java.types;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.AbstractSingletonScope;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.PrimitiveTypeId;
import org.eclipse.ocl.pivot.ids.SingletonScope.AbstractKeyAndValue;
import org.eclipse.ocl.pivot.internal.ids.UnscopedId;

/**
 * A JavaTypeId enables a Java class to be represented as a typeid singleton.
 */
public class JavaTypeId extends UnscopedId implements PrimitiveTypeId
{
	private static class JavaTypeIdValue extends AbstractKeyAndValue<@NonNull JavaTypeId>
	{
		private final @NonNull Class<?> value;

		public JavaTypeIdValue(@NonNull Class<?> value) {
			super(value.getName().hashCode());
			this.value = value;
		}

		@Override
		public @NonNull JavaTypeId createSingleton() {
			return new JavaTypeId(value);
		}

		@Override
		public boolean equals(@Nullable Object that) {
			if (that instanceof JavaTypeId) {
				JavaTypeId singleton = (JavaTypeId)that;
				return singleton.getJavaClass().equals(value);
			}
			else {
				return false;
			}
		}
	}

	public static class JavaTypeIdSingletonScope extends AbstractSingletonScope<@NonNull JavaTypeId, @NonNull Class<?>>
	{
		public @NonNull JavaTypeId getSingleton(@NonNull Class<?> value) {
			return getSingletonFor(new JavaTypeIdValue(value));
		}
	}

	protected final @NonNull Class<?> javaClass;

	public JavaTypeId(@NonNull Class<?> javaClass) {
		super(javaClass.getName());
		this.javaClass = javaClass;
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitPrimitiveTypeId(this);
	}

	public @NonNull Class<?> getJavaClass() {
		return javaClass;
	}

	@Override
	public @NonNull String getMetaTypeName() {
		return "JavaClass";
	}
}