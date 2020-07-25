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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.xtext.base.cs2text.elements.AlternativeAssignedKeywordsSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedKeywordSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.elements.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.elements.SequenceSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.NullEnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityExpression;

import com.google.common.collect.Lists;

public class UserSlotsAnalysis
{
	public static interface UserSlotAnalysis
	{
		int asCounted();
		int asEnumerated(@NonNull EnumerationValue enumerationValue);
		boolean isCounted();
		boolean isEnumerated();
	}

	public class CountedSlotAnalysis implements UserSlotAnalysis
	{
		// Empty, default-less slot
		public static final int ZERO = 0;
		// Explicitly or implicitly non-default slot
		public static final int ONE = 1;

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

	public class EnumeratedSlotAnalysis implements UserSlotAnalysis
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

	protected final @NonNull EObject eObject;
	protected final @Nullable Iterable<@NonNull SerializationRule> serializationRules;
	private final @NonNull Map<@NonNull EStructuralFeature, @NonNull UserSlotAnalysis> eStructuralFeature2slotAnalysis;

	public UserSlotsAnalysis(@Nullable Iterable<@NonNull SerializationRule> serializationRules, @NonNull EObject eObject) {
		this.eObject = eObject;
		this.serializationRules = serializationRules;
		Map<@NonNull EStructuralFeature, @NonNull UserSlotAnalysis> eStructuralFeature2slotAnalysis = new HashMap<>();
		if (serializationRules != null) {
			EClass eClass = eObject.eClass();
			for (EStructuralFeature eFeature : eClass.getEAllStructuralFeatures()) {
				assert eFeature != null;
				if ("stereotype".equals(eFeature.getName())) {
					getClass();			// XXX debugging
				}
				if (!eFeature.isDerived() && !eFeature.isTransient() && !eFeature.isVolatile()) {
					Object object = eObject.eGet(eFeature);
					UserSlotAnalysis slotAnalysis = null;
					if (eFeature instanceof EReference) {
						EReference eReference = (EReference)eFeature;
						if (!eReference.isContainer()) {
							if (eFeature.isMany()) {
								List<?> elements = (List<?>)object;
								int size = elements.size();
								slotAnalysis = valueOf(size);
							}
							else {
								slotAnalysis = valueOf(object != null ? CountedSlotAnalysis.ONE : CountedSlotAnalysis.ZERO);
							}
						}
					}
					else {
						EAttribute eAttribute = (EAttribute)eFeature;
						if (eAttribute.isMany()) {
							Iterable<@NonNull EnumerationValue> enumerationValues = getEnumerationValues(eAttribute);
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
								enumeratedSlotAnalysis.put(NullEnumerationValue.INSTANCE, others);
								slotAnalysis = enumeratedSlotAnalysis;
							}
							else {
								slotAnalysis = valueOf(size);
							}
						}
						else if (object instanceof Boolean) {
							// NB Xtext has no ability to explicitly define a false Boolean.
							slotAnalysis = valueOf(object == Boolean.TRUE ? CountedSlotAnalysis.ONE : CountedSlotAnalysis.ZERO);
						}
						else if (eObject.eIsSet(eAttribute)) {
							Iterable<@NonNull EnumerationValue> enumerationValues = getEnumerationValues(eAttribute);
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
								enumeratedSlotAnalysis.put(NullEnumerationValue.INSTANCE, gotOne ? 0 : 1);
								slotAnalysis = enumeratedSlotAnalysis;
							}
							else {
								slotAnalysis = valueOf(CountedSlotAnalysis.ONE);
							}
						}
						else if (eAttribute.isUnsettable()) {
							slotAnalysis = valueOf(CountedSlotAnalysis.ZERO);
						}
						else {
							boolean allRulesNeedDefault = true;
							for (@NonNull SerializationRule serializationRule : serializationRules) {
								if (!serializationRule.getBasicSerializationRule().getPreSerializer().needsDefault(eFeature)) {
									allRulesNeedDefault = false;
									break;
								}
							}
							slotAnalysis = valueOf(allRulesNeedDefault ? CountedSlotAnalysis.ONE : CountedSlotAnalysis.ZERO);
						}
					}
					if (slotAnalysis != null) {
						eStructuralFeature2slotAnalysis.put(eFeature, slotAnalysis);
					}
				}
			}
		}
		this.eStructuralFeature2slotAnalysis = eStructuralFeature2slotAnalysis;
	}

	public @Nullable UserSlotAnalysis basicGetSlotAnalysis(@NonNull EStructuralFeature eStructuralFeature) {
		return eStructuralFeature2slotAnalysis.get(eStructuralFeature);
	}

	public void diagnose(@NonNull StringBuilder s) {
		Iterable<@NonNull SerializationRule> serializationRules2 = serializationRules;
		if (serializationRules2 == null) {
			s.append(" - No serialization rules.");
			return;
		}
		char c = 'A';
		for (@NonNull SerializationRule serializationRule : serializationRules2) {
			s.append("\n  [");
			s.append("" + c++);
			s.append("] ");
			serializationRule.toRuleString(s);
		}
		s.append("\n");
		c = 'A';
		s.append(String.format("%-30.30s%9s", "feature", "actual"));
	//	Set<@NonNull EStructuralFeature> allFeatures = new HashSet<>();
		for (@SuppressWarnings("unused") @NonNull SerializationRule serializationRule : serializationRules2) {
			s.append(" [");
			s.append("" + c++);
			s.append("]");
	//		for (@NonNull EStructuralFeature eStructuralFeature : serializationRule.getEStructuralFeatures()) {
	//			allFeatures.add(eStructuralFeature);
	//		}
		}
		List<@NonNull EStructuralFeature> sortedFeatures = new ArrayList<>(eStructuralFeature2slotAnalysis.keySet());
		Collections.sort(sortedFeatures, NameUtil.ENAMED_ELEMENT_COMPARATOR);
		for (@NonNull EStructuralFeature eStructuralFeature : sortedFeatures) {
			s.append("\n");
			int size = CardinalityExpression.getSize(this, eStructuralFeature, NullEnumerationValue.INSTANCE);
			s.append(String.format("%-30.30s%8d", eStructuralFeature.getName(), size));
			for (@NonNull SerializationRule serializationRule : serializationRules2) {
				BasicSerializationRule basicSerializationRule = serializationRule.getBasicSerializationRule();
				MultiplicativeCardinality multiplicativeCardinality = basicSerializationRule.getMultiplicativeCardinality(eStructuralFeature, NullEnumerationValue.INSTANCE);
				s.append(String.format("%4s", multiplicativeCardinality != null ? multiplicativeCardinality.toString() : "0"));
			}
			if (eStructuralFeature instanceof EAttribute) {
				Iterable<@NonNull EnumerationValue> enumerationValues = getEnumerationValues((EAttribute)eStructuralFeature);
				if (enumerationValues != null) {
					List<@NonNull EnumerationValue> sortedEnumerationValues = Lists.newArrayList(enumerationValues);
					Collections.sort(sortedEnumerationValues, NameUtil.NAMEABLE_COMPARATOR);
					for (@NonNull EnumerationValue enumerationValue : sortedEnumerationValues) {
						int size2 = CardinalityExpression.getSize(this, eStructuralFeature, enumerationValue);
						s.append(String.format("\n %-29.29s%8d", "\"" + enumerationValue.getName() + "\"", size2));
						for (@NonNull SerializationRule serializationRule : serializationRules2) {
							BasicSerializationRule basicSerializationRule = serializationRule.getBasicSerializationRule();
							MultiplicativeCardinality multiplicativeCardinality = basicSerializationRule.getMultiplicativeCardinality(eStructuralFeature, enumerationValue);
							s.append(String.format("%4s", multiplicativeCardinality != null ? multiplicativeCardinality.toString() : "0"));
						}
					}
				}
			}
		}
		s.append("\n");
	}

	public @NonNull EObject getEObject() {
		return eObject;
	}

	public @NonNull Iterable<@NonNull EStructuralFeature> getEStructuralFeatures() {
		return eStructuralFeature2slotAnalysis.keySet();
	}

	protected @Nullable Iterable<@NonNull EnumerationValue> getEnumerationValues(@NonNull EAttribute eAttribute) {
		Set<@NonNull EnumerationValue> enumerationValues = new HashSet<>();
		assert serializationRules != null;
		for (@NonNull SerializationRule serializationRule : serializationRules) {
		//	for (@NonNull SerializationNode serializationNode : Collections.singletonList(serializationRule.getRootSerializationNode())) {
				getEnumerationValues(eAttribute, serializationRule.getRootSerializationNode(), enumerationValues);
		//	}
		}
		return enumerationValues.isEmpty() ? null : enumerationValues;
	}

	private void getEnumerationValues(@NonNull EAttribute eAttribute, @NonNull SerializationNode serializationNode, @NonNull Set<@NonNull EnumerationValue> enumerationValues) {
		if (serializationNode instanceof AlternativeAssignedKeywordsSerializationNode) {
			AlternativeAssignedKeywordsSerializationNode assignedKeywordsSerializationNode = (AlternativeAssignedKeywordsSerializationNode)serializationNode;
			if (assignedKeywordsSerializationNode.getEStructuralFeature() == eAttribute) {
				EnumerationValue enumerationValue = assignedKeywordsSerializationNode.getEnumerationValue();
				if (!enumerationValue.isNull()) {
					enumerationValues.add(enumerationValue);
				}
			}
		}
		else if (serializationNode instanceof AssignedKeywordSerializationNode) {
			AssignedKeywordSerializationNode assignedKeywordSerializationNode = (AssignedKeywordSerializationNode)serializationNode;
			if (assignedKeywordSerializationNode.getEStructuralFeature() == eAttribute) {
				EnumerationValue enumerationValue = assignedKeywordSerializationNode.getEnumerationValue();
				if (!enumerationValue.isNull()) {
					enumerationValues.add(enumerationValue);
				}
			}
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				getEnumerationValues(eAttribute, nestedSerializationNode, enumerationValues);
			}
		}
	}

	public @NonNull UserSlotAnalysis getSlotAnalysis(@NonNull EStructuralFeature eStructuralFeature) {
		return ClassUtil.nonNullState(eStructuralFeature2slotAnalysis.get(eStructuralFeature));
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
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
		return s.toString();
	}

	private final @NonNull CountedSlotAnalysis ZERO = new CountedSlotAnalysis(CountedSlotAnalysis.ZERO);
	private final @NonNull CountedSlotAnalysis ONE = new CountedSlotAnalysis(CountedSlotAnalysis.ONE);

	public @NonNull CountedSlotAnalysis valueOf(int value) {
		switch (value) {
			case CountedSlotAnalysis.ZERO: return ZERO;
			case CountedSlotAnalysis.ONE: return ONE;
			default: return new CountedSlotAnalysis(value);
		}
	}
}