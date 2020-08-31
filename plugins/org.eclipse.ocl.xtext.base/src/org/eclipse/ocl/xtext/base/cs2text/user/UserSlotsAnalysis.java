/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.cs2text.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.OthersEnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue;

public class UserSlotsAnalysis
{
	/**
	 * A UserSlotAnalysis provides the actual run-time analysis of a particular user element slot.
	 *
	 * Derived classes implement distinct approach for counting slot content.
	 */
	public static interface UserSlotAnalysis
	{
		/**
		 * Return the number of slot elements for a CountedSlotAnalysis or throw an IllegalStateException otherwose.
		 */
		int asCounted();

		/**
		 * Return the number of enumerationValue slot elements for an EnmeratedSlotAnalysis or throw an IllegalStateException otherwose.
		 */
		int asEnumerated(@NonNull EnumerationValue enumerationValue);

		/**
		 * Return true if this is a CountedSlotAnalysis.
		 */
		boolean isCounted();

		/**
		 * Return true if this is an EnmeratedSlotAnalysis.
		 */
		boolean isEnumerated();
	}

	/**
	 * A CountedSlotAnalysis provides the default analysis of a slot of indeterminate compile-time content.
	 * The analysis provides a simple count of the actual number of slots.
	 */
	public static class CountedSlotAnalysis implements UserSlotAnalysis
	{
		private static final @NonNull CountedSlotAnalysis ZERO = new CountedSlotAnalysis(0);
		private static @NonNull CountedSlotAnalysis ONE = new CountedSlotAnalysis(1);

		public static @NonNull CountedSlotAnalysis valueOf(int value) {
			switch (value) {
				case 0: return ZERO;
				case 1: return ONE;
				default: return new CountedSlotAnalysis(value);
			}
		}

		protected final int count;

		private CountedSlotAnalysis(int count) {
			this.count = count;
		}

		@Override
		public int asCounted() {
			return count >= 0 ? count : 0;
		}

		@Override
		public int asEnumerated(@NonNull EnumerationValue enumerationValue) {
			throw new IllegalStateException();
		}

		@Override
		public boolean isCounted() {
			return true;
		}

		@Override
		public boolean isEnumerated() {
			return false;
		}

		@Override
		public @NonNull String toString() {
			return Integer.toString(count);
		}
	}

	/**
	 * An EnumeratedSlotAnalysis provides the analysis of a (typically many)-valued String EAttribute slot
	 * whose string values are defined at compile-time by the grammar. The String value therefore acts as an
	 * enumeration and is maintained as an EnumerationValue.
	 */
	public static class EnumeratedSlotAnalysis implements UserSlotAnalysis
	{
		private final @NonNull Map<@NonNull EnumerationValue, @NonNull Integer> enumerationValue2count = new HashMap<>();

		public @Nullable Integer basicGet(@NonNull EnumerationValue enumerationValue) {
			return enumerationValue2count.get(enumerationValue);
		}

		@Override
		public int asCounted() {
			throw new IllegalStateException();
		}

		@Override
		public int asEnumerated(@NonNull EnumerationValue enumerationValue) {
			Integer value = enumerationValue2count.get(enumerationValue);
			return value != null ? value.intValue() : 0;
		}

		@Override
		public boolean isCounted() {
			return false;
		}

		@Override
		public boolean isEnumerated() {
			return true;
		}

		public void put(@NonNull EnumerationValue enumerationValue, int count) {
			enumerationValue2count.put(enumerationValue, count);
		}

		@Override
		public @NonNull String toString() {
			StringBuilder s = new StringBuilder();
			List<@NonNull EnumerationValue> keys  = new ArrayList<>(enumerationValue2count.keySet());
			Collections.sort(keys, NameUtil.NAMEABLE_COMPARATOR);
			boolean isFirst = true;
			for (@NonNull EnumerationValue key : keys) {
				if (!isFirst) {
					s.append(",");
				}
				s.append(key);
				s.append("=");
				s.append(enumerationValue2count.get(key));
				isFirst = false;
			}
			return s.toString();
		}
	}

	protected final @NonNull UserModelAnalysis modelAnalysis;
	protected final @NonNull EObject eObject;
	protected final @Nullable DynamicSerializationRules serializationRules;
	private final @NonNull Map<@NonNull EStructuralFeature, @NonNull UserSlotAnalysis> eStructuralFeature2slotAnalysis;

	/**
	 * Cache of DynamicRuleMatch per StaticRuleMatch. This cache reduces the DynamicRuleMatch per StaticRuleMatch ratio
	 * from nearly 10 to less than 2 by re-using the lookahead from the tree descent.
	 */
	private @NonNull List<@NonNull DynamicRuleMatch> dynamicRuleMatches = new ArrayList<>();

	public UserSlotsAnalysis(@NonNull UserModelAnalysis modelAnalysis, @Nullable DynamicSerializationRules serializationRules, @NonNull EObject eObject) {
		this.modelAnalysis = modelAnalysis;
		this.eObject = eObject;
		this.serializationRules = serializationRules;
		this.eStructuralFeature2slotAnalysis = analyze();
		modelAnalysis.debugAddUserSlotsAnalysis(this);
	}

	public void addDynamicRuleMatch(@NonNull DynamicRuleMatch dynamicRuleMatch) {
		dynamicRuleMatches.add(dynamicRuleMatch);
	}

	protected @NonNull Map<@NonNull EStructuralFeature, @NonNull UserSlotAnalysis> analyze() {
		Map<@NonNull EStructuralFeature, @NonNull UserSlotAnalysis> eStructuralFeature2slotAnalysis = new HashMap<>();
		DynamicSerializationRules serializationRules2 = serializationRules;
		if (serializationRules2 != null) {
			EClass eClass = eObject.eClass();
			for (EStructuralFeature eFeature : eClass.getEAllStructuralFeatures()) {
				assert eFeature != null;
				if (!eFeature.isDerived() && !eFeature.isTransient() && !eFeature.isVolatile()) {
					UserSlotAnalysis slotAnalysis = null;
					if (eFeature instanceof EReference) {
						slotAnalysis = analyzeEReference((EReference)eFeature);
					}
					else {
						slotAnalysis = analyzeEAttribute((EAttribute)eFeature);
					}
					if (slotAnalysis != null) {
						eStructuralFeature2slotAnalysis.put(eFeature, slotAnalysis);
					}
				}
			}
		}
		return eStructuralFeature2slotAnalysis;
	}

	protected @NonNull UserSlotAnalysis analyzeEAttribute(@NonNull EAttribute eAttribute) {
		DynamicSerializationRules serializationRules2 = serializationRules;
		assert serializationRules2 != null;
		UserSlotAnalysis slotAnalysis;
		Object object = eObject.eGet(eAttribute);
		if (eAttribute.isMany()) {
			Iterable<@NonNull EnumerationValue> enumerationValues = serializationRules2.getEnumerationValues(eAttribute);
			List<?> elements = (List<?>)object;
			int size = elements.size();
			if ((size > 0) && (enumerationValues != null)) {
				EnumeratedSlotAnalysis enumeratedSlotAnalysis = new EnumeratedSlotAnalysis();
				int others = 0;
				for (Object element : elements) {
					String string = String.valueOf(element);
					boolean gotOne = false;
					for (@NonNull EnumerationValue enumerationValue : enumerationValues) {
						if (enumerationValue.isElement(string)) {
							Integer count = enumeratedSlotAnalysis.basicGet(enumerationValue);
							enumeratedSlotAnalysis.put(enumerationValue, (count == null ? 0 : count.intValue()) + 1);
							gotOne = true;
						}
					}
					if (!gotOne) {
						others++;
					}
				}
				enumeratedSlotAnalysis.put(OthersEnumerationValue.INSTANCE, others);
				slotAnalysis = enumeratedSlotAnalysis;
			}
			else {
				slotAnalysis = CountedSlotAnalysis.valueOf(size);
			}
		}
		else if (object instanceof Boolean) {
			// NB Xtext has no ability to explicitly define a false Boolean.
			slotAnalysis = CountedSlotAnalysis.valueOf(object == Boolean.TRUE ? 1 : 0);
		}
		else if (eObject.eIsSet(eAttribute)) {
			Iterable<@NonNull EnumerationValue> enumerationValues = serializationRules2.getEnumerationValues(eAttribute);
			if (enumerationValues != null) {
				EnumeratedSlotAnalysis enumeratedSlotAnalysis = new EnumeratedSlotAnalysis();
				String string = String.valueOf(object);
				boolean gotOne = false;
				for (@NonNull EnumerationValue enumerationValue : enumerationValues) {
					if (enumerationValue.isElement(string)) {
						enumeratedSlotAnalysis.put(enumerationValue, 1);
						gotOne = true;
					}
				}
				enumeratedSlotAnalysis.put(OthersEnumerationValue.INSTANCE, gotOne ? 0 : 1);
				slotAnalysis = enumeratedSlotAnalysis;
			}
			else {
				slotAnalysis = CountedSlotAnalysis.valueOf(1);
			}
		}
		else if (eAttribute.isUnsettable()) {
			slotAnalysis = CountedSlotAnalysis.valueOf(0);
		}
		else {
			boolean allRulesNeedDefault = serializationRules2.allRulesNeedDefault(eAttribute);
			slotAnalysis = CountedSlotAnalysis.valueOf(allRulesNeedDefault ? 1 : 0);
		}
		return slotAnalysis;
	}

	protected @Nullable UserSlotAnalysis analyzeEReference(@NonNull EReference eReference) {
		if (eReference.isContainer()) {
			return null;
		}
		Object object = eObject.eGet(eReference);
		if (eReference.isMany()) {
			List<?> elements = (List<?>)object;
			assert elements != null;
			int size = elements.size();
			return CountedSlotAnalysis.valueOf(size);
		}
		else {
			return CountedSlotAnalysis.valueOf(object != null ? 1 : 0);
		}
	}

	public @Nullable DynamicRuleMatch basicGetDynamicRuleMatch(@NonNull Object staticRuleMatch) {
		// Typically one or perhaps two entries; not worth a Map.
		for (@NonNull DynamicRuleMatch dynamicRuleMatch : dynamicRuleMatches) {
			if (dynamicRuleMatch.getDebugStaticRuleMatch() == staticRuleMatch) {
				return dynamicRuleMatch;
			}
		}
		return null;
	}

	public @Nullable UserSlotAnalysis basicGetSlotAnalysis(@NonNull EStructuralFeature eStructuralFeature) {
		return eStructuralFeature2slotAnalysis.get(eStructuralFeature);
	}

	public void diagnose(@NonNull StringBuilder s) {
		DynamicSerializationRules serializationRules2 = serializationRules;
		if (serializationRules2 == null) {
			s.append(" - No serialization rules.");
			return;
		}
		serializationRules2.diagnose(s, this);
	}

	public @NonNull EObject getEObject() {
		return eObject;
	}

	public @NonNull Iterable<@NonNull EStructuralFeature> getEStructuralFeatures() {
		return eStructuralFeature2slotAnalysis.keySet();
	}

	public @NonNull UserModelAnalysis getModelAnalysis() {
		return modelAnalysis;
	}

	public int getSize(@NonNull EStructuralFeature eStructuralFeature) {
		UserSlotAnalysis slotAnalysis = basicGetSlotAnalysis(eStructuralFeature);
		if (slotAnalysis == null) {
			return 0;
		}
		if (slotAnalysis.isCounted()) {
			return slotAnalysis.asCounted();
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	public int getSize(@NonNull EAttribute eAttribute, @NonNull EnumerationValue enumerationValue) {
		UserSlotAnalysis slotAnalysis = basicGetSlotAnalysis(eAttribute);
		if (slotAnalysis == null) {
			return 0;
		}
		if (slotAnalysis.isCounted()) {
			return slotAnalysis.asCounted();
		}
		else if (slotAnalysis.isEnumerated()) {
			return slotAnalysis.asEnumerated(enumerationValue);
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	public int getSize(@NonNull EReference eReference, @NonNull ParserRuleValue parserRuleValue) {
		UserSlotAnalysis slotAnalysis = basicGetSlotAnalysis(eReference);
		if (slotAnalysis == null) {
			return 0;
		}
		if (slotAnalysis.isCounted()) {
			return slotAnalysis.asCounted();
		}
		throw new UnsupportedOperationException();
	}

	public @NonNull UserSlotAnalysis getSlotAnalysis(@NonNull EStructuralFeature eStructuralFeature) {
		return ClassUtil.nonNullState(eStructuralFeature2slotAnalysis.get(eStructuralFeature));
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		if (ClassUtil.maybeNull(eStructuralFeature2slotAnalysis) != null) {
			List<@NonNull EStructuralFeature> keys  = new ArrayList<>(eStructuralFeature2slotAnalysis.keySet());
			Collections.sort(keys, NameUtil.ENAMED_ELEMENT_COMPARATOR);
			boolean isFirst = true;
			for (@NonNull EStructuralFeature key : keys) {
				if (!isFirst) {
					s.append(", ");
				}
				s.append(key.getName());
				s.append("=");
				s.append(eStructuralFeature2slotAnalysis.get(key));
				isFirst = false;
			}
		}
	}
}