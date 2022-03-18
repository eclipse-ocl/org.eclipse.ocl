/*******************************************************************************
 * Copyright (c) 2012, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library.executor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.InheritanceFragment;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.utilities.FeatureFilter;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;


/**
 * @since 1.18
 */
public class ReflectiveProperties
{
	protected final @NonNull CompleteInheritance inheritance;
	protected final @NonNull Map<@NonNull String, @NonNull Property> name2property = new HashMap<>();

	public ReflectiveProperties(@NonNull CompleteInheritance inheritance) {
		this.inheritance = inheritance;
		InheritanceFragment selfFragment = inheritance.getSelfFragment();
		for (@NonNull Property asProperty : selfFragment.getLocalProperties()) {
			name2property.put(PivotUtil.getName(asProperty), asProperty);
		}
	}

	public @NonNull Iterable<@NonNull Property> getAllProperties(final @Nullable FeatureFilter featureFilter) {
		@NonNull Collection<@NonNull Property> values = name2property.values();
		if (featureFilter == null) {
			return values;
		}
		@NonNull Iterable<@NonNull Property> subItOps = Iterables.filter(values, new Predicate<@NonNull Property>()
		{
			@Override
			public boolean apply(@NonNull Property asProperty) {
				return featureFilter.accept(asProperty);
			}
		});
		return subItOps;
	}

	public @Nullable Property getMemberProperty(@NonNull String name) {
		return name2property.get(name);
	}
}
