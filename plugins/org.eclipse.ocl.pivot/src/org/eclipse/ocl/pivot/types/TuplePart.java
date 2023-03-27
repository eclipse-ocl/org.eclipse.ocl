/*******************************************************************************
 * Copyright (c) 2023 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.utilities.Nameable;

/**
 * TuplePart provides the minimal interface for constructing a TupleType witout imposing the overheads and
 * inappropriate construction order of a regular TypedElementImpl.
 */
public interface TuplePart extends Nameable
{
	Type getType();

	public static class TuplePartImpl implements TuplePart
	{
		protected final @NonNull String name;
		protected final @NonNull Type type;

		public TuplePartImpl(@NonNull String name, @NonNull Type type) {
			this.name = name;
			this.type = type;
		}

		@Override
		public @NonNull String getName() {
 			return name;
		}

		@Override
		public @NonNull Type getType() {
			return type;
		}

		@Override
		public @NonNull String toString() {
			return name + " : " + type;
		}
	}

}