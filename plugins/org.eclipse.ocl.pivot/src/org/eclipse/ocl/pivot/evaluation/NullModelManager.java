/*******************************************************************************
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.evaluation;

import java.util.Collections;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * A NullModelManager provides no models. It is unlikely to be useful, but may forestall a fatal crash
 * allowing more useful diagnosis.
 *
 * @since 7.0
 */
public class NullModelManager implements ModelManager
{
	public static final @NonNull NullModelManager INSTANCE = new NullModelManager();

	@Override
	public void dispose() {}

	@Override
	public @NonNull TreeIterator<? extends Object> eAllContents(@NonNull Object object) {
		throw new UnsupportedOperationException();			// XXX
	}

	@Override
	public @NonNull EClass eClass(@NonNull Object object) {
		throw new UnsupportedOperationException();			// XXX
	}

	@Override
	public @Nullable Object eContainer(@NonNull Object object) {
		return null;
	}

	@Override
	public @Nullable Object eGet(@NonNull Object object, @NonNull EStructuralFeature eFeature) {
		return null;
	}

	@Override
	public @NonNull Iterable<@NonNull ? extends Object> getInstances(org.eclipse.ocl.pivot.@NonNull Class type) {
		return Collections.<@NonNull Object>emptySet();
	}
}