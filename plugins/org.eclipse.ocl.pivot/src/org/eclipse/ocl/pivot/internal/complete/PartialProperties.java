/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.complete;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.scoping.EnvironmentView;
import org.eclipse.ocl.pivot.internal.scoping.EnvironmentView.Disambiguator;

public class PartialProperties //implements Iterable<@NonNull Property>
{
	// resolution = null, partials = null or empty => empty
	// resolution = X, partials = null or empty or {X} => X
	// resolution = null, partials not empty => lazy unresolved 'ambiguity'
	private boolean isResolved = false;
	private @Nullable Property resolution = null;
	private @Nullable List<@NonNull Property> partials = null;
	protected final @NonNull StandardLibrary standardLibrary;

	public PartialProperties(@NonNull StandardLibrary standardLibrary) {
		this.standardLibrary = standardLibrary;
	}

	public synchronized void didAddProperty(@NonNull Property pivotProperty) {
		List<@NonNull Property> partials2 = partials;
		@Nullable Property resolution2 = resolution;
		if (partials2 == null) {
			if (resolution2 == null) {
				resolution2 = resolution = pivotProperty;
				isResolved = true;
			}
			else {
				partials = partials2 = new ArrayList<>();
				partials2.add(resolution2);
				if (resolution2 != pivotProperty) {
					partials2.add(pivotProperty);
				}
				resolution2 = resolution = null;
				isResolved = false;
			}
		}
		else if (partials2.isEmpty()) {
			if (resolution2 == null) {
				resolution2 = resolution = pivotProperty;
				isResolved = true;
			}
			else {
				partials2.add(resolution2);
				if (resolution2 != pivotProperty) {
					partials2.add(pivotProperty);
				}
				resolution2 = resolution = null;
				isResolved = false;
			}
		}
		else {
			if (!partials2.contains(pivotProperty)) {
				partials2.add(pivotProperty);
			}
			resolution2 = resolution = null;
			isResolved = false;
		}
	}

	public boolean didRemoveProperty(@NonNull Property pivotProperty) {
		remove(pivotProperty);
		return isEmpty();
	}

	public synchronized @Nullable Property get() {
		if (isResolved) {
			return resolution;
		}
		resolve();
		if (isResolved) {
			return resolution;
		}
		List<@NonNull Property> values = new ArrayList<>(partials);
		Map<@NonNull Type, @NonNull Property> primaryProperties = new HashMap<>();
		for (@NonNull Property property : values) {
			org.eclipse.ocl.pivot.Class owningType = property.getOwningClass();
			if (owningType != null) {
			//	Type domainType = environmentFactory.getMetamodelManager().getPrimaryType(owningType);
				Type domainType = standardLibrary.getFlatModel().getPrimaryType(owningType);
				if (!primaryProperties.containsKey(domainType)) {
					primaryProperties.put(domainType, property);	// FIXME something more deterministic than first
				}
			}
		}
		if (primaryProperties.size() == 1) {
			resolution = primaryProperties.values().iterator().next();
			isResolved = true;
			return resolution;
		}
		isResolved = true;
		resolution = null;
		return resolution;
	}

	/**
	 * @since 1.5
	 */
	public @Nullable Iterable<@NonNull Property> getPartials() {
		return partials != null ? partials : null;
	}

	public synchronized boolean isEmpty() {
		if (resolution != null) {
			return false;
		}
		List<@NonNull Property> partials2 = partials;
		if (partials2 == null) {
			return true;
		}
		return partials2.size() <= 0;
	}

/*	@Override
	public @NonNull Iterator<@NonNull Property> iterator() {
		if (!isResolved) {
			resolve();
		}
		if (resolution != null) {
			return Iterators.singletonIterator(resolution);
		}
		else if (partials != null) {
			return partials.iterator();
		}
		else {
			return ClassUtil.emptyIterator();
		}
	} */

	public synchronized void remove(@NonNull Property pivotProperty) {
		if (pivotProperty == resolution) {
			resolution = null;
		}
		if (partials != null) {
			partials.remove(pivotProperty);
		}
	}

	private void resolve() {
		assert !isResolved;
		List<@NonNull Property> partials2 = partials;
		if (partials2 == null) {
			return;
		}
		int size = partials2.size();
		if (size <= 0) {
			return;
		}
		if (size == 1) {
			isResolved = true;
			resolution = partials2.get(0);
		}
		List<@NonNull Property> values = new ArrayList<>(partials);
		for (int i = 0; i < values.size()-1;) {
			boolean iRemoved = false;
			@NonNull Property iValue = values.get(i);
			for (int j = i + 1; j < values.size();) {
				Class<? extends Property> iClass = iValue.getClass();
				@NonNull Property jValue = values.get(j);
				Class<? extends Property> jClass = jValue.getClass();
				int verdict = 0;
				for (Class<?> key : EnvironmentView.getDisambiguatorKeys()) {
					if (key.isAssignableFrom(iClass) && key.isAssignableFrom(jClass)) {
						List<@NonNull Comparator<@NonNull Object>> disambiguators = EnvironmentView.getDisambiguators(key);
						if (disambiguators != null) {
							for (@NonNull Comparator<@NonNull Object> comparator : disambiguators) {
								if (comparator instanceof Disambiguator<?>) {
									verdict = ((Disambiguator<@NonNull Object>)comparator).compare(standardLibrary, iValue, jValue);
								}
								else {
									verdict = comparator.compare(iValue, jValue);
								}
								if (verdict != 0) {
									break;
								}
							}
						}
						if (verdict != 0) {
							break;
						}
					}
				}
				if (verdict == 0) {
					j++;
				} else if (verdict < 0) {
					values.remove(i);
					iRemoved = true;
					break;
				} else {
					values.remove(j);
				}
			}
			if (!iRemoved) {
				i++;
			}
		}
		if (values.size() == 1) {
			resolution = values.get(0);
			isResolved = true;
			return;
		}
	}

	@Override
	public String toString() {
		if (resolution != null) {
			return resolution.toString();
		}
		List<@NonNull Property> partials2 = partials;
		if (partials2 == null) {
			return "";
		}
		boolean isFirst = true;
		StringBuilder s = new StringBuilder();
		s.append("{");
		for (@NonNull Property dProperty : partials2) {
			if (!isFirst) {
				s.append(",");
			}
			s.append(dProperty.toString());
			isFirst = false;
		}
		s.append("}");
		return s.toString();
	}
}