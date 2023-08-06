/*******************************************************************************
 * Copyright (c) 2014, 2020 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D. Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.values;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.Type;

/**
 * TemplateParameterSubstitutions defines the interaction with the matching of formal TemplateParameters and actual types.
 * Formal template parameters are identified by theit integer intexes in a flattened list starting at the outer-most TemplateableElement.
 * The template parameter indexes in Collection(T1)::product(T2)(...)... are therefore 0 for T1, and 1 for T2.
 * <p>
 * A derived TemplateParameterSubstitutionVisitor determines the bindings by recursive analysis of a pair of formal and actual expression/type trees.
 * <p>
 * The EMPTY instance handles the degenerate case of no template parameters.
 */
public interface TemplateParameterSubstitutions
{
	/**
	 * Return the highest common actual type of the formal templateParameter, returning null if unknown.
	 */
	@Nullable Type get(@Nullable TemplateParameter templateParameter);

	/**
	 * Return the feature value associated with the templateParameter. Returns null if not known..
	 */
	default @Nullable Object getValue(@Nullable TemplateParameter templateParameter, /*@NonNull*/ EStructuralFeature feature) {
		return null;
	}

	/**
	 * Return the mapof per-feature values associated with the templateParameter. Returns null if not known..
	 */
	default @Nullable Map<@NonNull EStructuralFeature, Object> getValues(@Nullable TemplateParameter templateParameter) {
		return null;
	}

	/**
	 * Return true if there are no formal TemplateParameters with actual values.
	 */
	boolean isEmpty();

	/**
	 * Install actualType as the resolutions of formalTemplateParameter, returning the highest common type of actualType
	 * and any pre-existing resolution.
	 */
	@Nullable Type put(@NonNull TemplateParameter formalTemplateParameter, @NonNull Type actualType);

	/**
	 * Associate value with the feature for a templatePatameter.
	 *
	 * Throws UnsupportedOperationException if feature values not supported.
	 */
	default Object putValue(@NonNull TemplateParameter templateParameter, /*@NonNull*/ EStructuralFeature feature, Object value) {
		throw new UnsupportedOperationException();
	}

	public static final @NonNull TemplateParameterSubstitutions EMPTY = new Empty();

	public static class Empty implements TemplateParameterSubstitutions
	{
		@Override
		public @Nullable Type get(@Nullable TemplateParameter templateParameter) {
			return null;
		}

		@Override
		public boolean isEmpty() {
			return true;
		}

		@Override
		public @NonNull Type put(@NonNull TemplateParameter formalTemplateParameter, @NonNull Type actualType) {
			return actualType;
		}

		@Override
		public String toString() {
			return "{}";
		}
	}

	public static class SimpleTemplateParameterSubstitutions implements TemplateParameterSubstitutions
	{
		protected final @NonNull Map<@NonNull TemplateParameter, @NonNull Type> formal2actual = new HashMap<>();
		protected /*@LazyNonNull*/ Map<@NonNull TemplateParameter, @NonNull Map<@NonNull EStructuralFeature, Object>> formal2feature2value = new HashMap<>();

		@Override
		public @Nullable Type get(@Nullable TemplateParameter templateParameter) {
			return formal2actual.get(templateParameter);
		}

		@Override
		public @Nullable Object getValue(@Nullable TemplateParameter templateParameter, /*@NonNull*/ EStructuralFeature feature) {
			assert feature != null;
			Map<@NonNull EStructuralFeature, Object> feature2value = formal2feature2value.get(templateParameter);
			if (feature2value == null) {
				return null;
			}
			return feature2value.get(feature);
		}

		@Override
		public @Nullable Map<@NonNull EStructuralFeature, Object> getValues(@Nullable TemplateParameter templateParameter) {
			return formal2feature2value.get(templateParameter);
		}

		@Override
		public boolean isEmpty() {
			return formal2actual.isEmpty();
		}

		@Override
		public @Nullable Type put(@NonNull TemplateParameter templateParameter, @NonNull Type actualType) {
			return formal2actual.put(templateParameter, actualType);
		}

		@Override
		public Object putValue(@NonNull TemplateParameter templateParameter, /*@NonNull*/ EStructuralFeature feature, Object value) {
			assert feature != null;
			Map<@NonNull EStructuralFeature, Object> feature2value = formal2feature2value.get(templateParameter);
			if (feature2value == null) {
				feature2value = new HashMap<>();
				formal2feature2value.put(templateParameter, feature2value);
			}
			return feature2value.put(feature, value);
		}

		@Override
		public String toString() {
			StringBuilder s = new StringBuilder();
			s.append("{");
			boolean isFirst = true;
			Collection<@NonNull TemplateParameter> keys = formal2actual.keySet();
			if (keys.size() > 1) {
				List<@NonNull TemplateParameter> sortedKeys = new ArrayList<>(keys);
				Collections.sort(sortedKeys, new Comparator<@NonNull TemplateParameter>()
				{
					@Override
					public int compare(@NonNull TemplateParameter o1, @NonNull TemplateParameter o2) {
						int i1 = o1.getTemplateParameterId().getIndex();
						int i2 = o2.getTemplateParameterId().getIndex();
						int diff = i1 - i2;
						if (diff != 0) {
							return diff;
						}
						String n1 = o1.getName();
						String n2 = o2.getName();
						return n1.compareTo(n2);
					}
				});
				keys = sortedKeys;
			}
			for (TemplateParameter index : keys) {
				if (!isFirst) {
					s.append("\n");
				}
				s.append(index + " => " + formal2actual.get(index));
				isFirst = false;
			}
			s.append("}");
			return s.toString();
		}
	}
}
