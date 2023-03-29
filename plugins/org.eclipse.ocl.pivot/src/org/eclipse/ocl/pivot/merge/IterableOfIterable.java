/*******************************************************************************
 * Copyright (c) 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
*******************************************************************************/
package org.eclipse.ocl.pivot.merge;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;

/**
 * An IterableOfIterable is just an Iterable of Iterable but solves problems of poor template diagnostics.
 */
public class IterableOfIterable<E>
{
	private @NonNull List<@NonNull Iterable<@NonNull E>> contents = new ArrayList<>();

	public IterableOfIterable() {}
}
