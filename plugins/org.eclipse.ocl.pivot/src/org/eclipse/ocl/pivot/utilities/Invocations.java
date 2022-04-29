/*******************************************************************************
 * Copyright (c) 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import java.util.Iterator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Type;

import com.google.common.collect.Iterables;

/**
 * Invocations (or rather its derived implementations) support the capture of a set of candidate
 * operation matches.
 *
 * For OCL the elements are always Operation, but for QVT they may be  Rule, so NamedElement as the common type.
 *
 * @since 1.18
 */
public interface Invocations extends Iterable<@NonNull NamedElement>
{
	default @Nullable NamedElement getSingleResult() {
		return null;
	}

	default @NonNull Type getSourceType() {
		throw new UnsupportedOperationException();
	}

	default @Nullable String getUnresolveableReason() {
		return null;
	}

	public static class ResolvedInvocation implements Invocations
	{
		protected final @NonNull Operation invocation;

		public ResolvedInvocation(@NonNull Operation invocation) {
			this.invocation = invocation;
		}

		@Override
		public @NonNull NamedElement getSingleResult() {
			return invocation;
		}

		@Override
		public @NonNull Type getSourceType() {
			return ClassUtil.nonNullState(invocation.getOwningClass());
		}

		@Override
		public @NonNull Iterator<@NonNull NamedElement> iterator() {
			return new SingletonIterator<@NonNull NamedElement>(invocation);
		}
	}

	public static class UnresolvedInvocations implements Invocations
	{
		protected final @NonNull Type asType;
		protected final @NonNull Iterable<@NonNull NamedElement> invocations;

		public UnresolvedInvocations(@NonNull Type asType, @NonNull Iterable<@NonNull NamedElement> invocations) {
			this.asType = asType;
			this.invocations = invocations;
		}

		@Override
		public @Nullable NamedElement getSingleResult() {
			return Iterables.size(invocations) == 1 ? invocations.iterator().next() : null;
		}

		@Override
		public @NonNull Type getSourceType() {
			return asType;
		}

		@Override
		public @NonNull Iterator<@NonNull NamedElement> iterator() {
			return invocations.iterator();
		}
	}

	public static class UnresolveableInvocations implements Invocations
	{
		protected final @NonNull String unresolveableReason;

		public UnresolveableInvocations(/*@NonNull*/ String unresolveableReasonTemplate, @Nullable Object @NonNull ... bindings) {
			this.unresolveableReason = StringUtil.bind(unresolveableReasonTemplate, bindings);
		}

		@Override
		public @NonNull String getUnresolveableReason() {
			return unresolveableReason;
		}

		@Override
		public @NonNull Iterator<@NonNull NamedElement> iterator() {
			throw new UnsupportedOperationException();
		}
	}
}