/*******************************************************************************
 * Copyright (c) 2013, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ids;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.AbstractSingletonScope;
import org.eclipse.ocl.pivot.ids.BindingsId;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.IdHash;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.SingletonScope.AbstractKeyAndValue;

/**
 * BindingsIdImpl provides a hashable list of elementIds suitable for use when indexing specializations.
 */
public class BindingsIdImpl implements BindingsId, WeakHashMapOfListOfWeakReference2.MatchableId<@NonNull ElementId @NonNull []>
{
	protected class Iterator implements java.util.Iterator<@NonNull ElementId>
	{
		private int index = 0;

		@Override
		public boolean hasNext() {
			return index < elementIds.length;
		}

		@Override
		public @NonNull ElementId next() {
			return elementIds[index++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private static class BindingsIdValue extends AbstractKeyAndValue<@NonNull BindingsId>
	{
		private final @NonNull IdManager idManager;
		private @NonNull ElementId @NonNull [] elements;

		private BindingsIdValue(@NonNull IdManager idManager, @NonNull ElementId @NonNull [] elements) {
			super(BindingsIdImpl.computeHashCode(elements));
			this.idManager = idManager;
			this.elements = elements;
		}

		@Override
		public @NonNull BindingsId createSingleton() {
			return new BindingsIdImpl(idManager, elements);
		}

		@Override
		public boolean equals(@Nullable Object that) {
			if (that instanceof BindingsIdImpl) {
				BindingsIdImpl singleton = (BindingsIdImpl)that;
				return singleton.matches(elements);
			}
			else {
				return false;
			}
		}
	}

	/**
	 * @since 1.18
	 */
	public static class BindingsIdSingletonScope extends AbstractSingletonScope<@NonNull BindingsId, @NonNull ElementId @NonNull []>
	{
		public @NonNull BindingsId getSingleton(@NonNull IdManager idManager, @NonNull ElementId @NonNull [] elements) {
			return getSingletonFor(new BindingsIdValue(idManager, elements));
		}
	}

	private static int computeHashCode(@NonNull ElementId @NonNull [] elementIds) {
		return IdHash.createParametersHash(BindingsId.class, elementIds);
	}

	private final @NonNull ElementId @NonNull [] elementIds;
	private final @NonNull Integer hashCode;

	/**
	 * Construct a BindingsId for an idManager that has computed the hashCode for the typeIds.
	 */
	@Deprecated /* @deprecated no longer used */
	public BindingsIdImpl(@NonNull IdManager idManager, @NonNull Integer hashCode, @NonNull ElementId @NonNull [] elementIds) {
		this.hashCode = hashCode;
		this.elementIds = elementIds;
		assert hashCode == this.hashCode;
	}

	private BindingsIdImpl(@NonNull IdManager idManager, @NonNull ElementId @NonNull [] elementIds) {
		this.hashCode = computeHashCode(elementIds);
		this.elementIds = elementIds;
	}

	@Override
	public final boolean equals(Object that) {
		if (that instanceof org.eclipse.ocl.pivot.ids.SingletonScope.KeyAndValue) {	// A SingletonScope.Key may be used to lookup a singleton
			return that.equals(this);
		}
		else {										// But normally ElementId instances are singletons
			return this == that;
		}
	}

	@Override
	public @NonNull ElementId get(int i) {
		return elementIds[i];
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public java.util.@NonNull Iterator<@NonNull ElementId> iterator() {
		return new Iterator();
	}

	@Override
	public boolean matches(@NonNull ElementId @NonNull [] thoseElementIds) {
		if (elementIds.length != thoseElementIds.length) {
			return false;
		}
		for (int i = 0; i < elementIds.length; i++) {
			if (elementIds[i] != thoseElementIds[i]) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int size() {
		return elementIds.length;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append('(');
		for (int i = 0; i < elementIds.length; i++) {
			if (i > 0) {
				s.append(',');
			}
			s.append(String.valueOf(elementIds[i]));
		}
		s.append(')');
		return s.toString();
	}
}
