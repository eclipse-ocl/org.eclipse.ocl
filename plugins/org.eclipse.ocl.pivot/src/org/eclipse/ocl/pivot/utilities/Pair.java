/*******************************************************************************
 * Copyright (c) 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	 E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * A simple Pair of two objects with a public constructor unlike the numerous alternatives.
 *
 * @since 1.18
 */
public final class Pair<T1, T2>
{
	private final T1 first;
	private final T2 second;
	private @Nullable Integer hash = null;

	public Pair(T1 first, T2 second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Pair)) {
			return false;
		}
		Pair<?, ?> that = (Pair<?, ?>)obj;
		T1 thisFirst = this.first;
		if (thisFirst == null) {
			if (that.first != null) {
				return false;
			}
		}
		else {
			if (that.first == null) {
				return false;
			}
			if (!thisFirst.equals(that.first)) {
				return false;
			}
		}
		T2 thisSecond = this.second;
		if (thisSecond == null) {
			if (that.second != null) {
				return false;
			}
		}
		else {
			if (that.second == null) {
				return false;
			}
			if (!thisSecond.equals(that.second)) {
				return false;
			}
		}
		return true;
	}

	public T1 getFirst() {
		return first;
	}

	public T2 getSecond() {
		return second;
	}

	@Override
	public int hashCode() {
		Integer hash2 = hash;
		if (hash2 == null) {
			int firstHash = first != null ? first.hashCode() : 0;
			int secondHash = second != null ? second.hashCode() : 0;
			hash2 = firstHash + 17 * secondHash;
		}
		return hash2.intValue();
	}

	@Override
	public @NonNull String toString() {
		return "Pair(" + first + ", " + second + ")";
	}
}
