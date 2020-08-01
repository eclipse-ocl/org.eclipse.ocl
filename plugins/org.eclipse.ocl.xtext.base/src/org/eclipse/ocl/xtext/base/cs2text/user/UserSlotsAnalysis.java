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
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;

import com.google.common.collect.Lists;

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
		 * Return the number of ruleAnalysis slot elements for an DiscriminatedSlotAnalysis or throw an IllegalStateException otherwose.
		 */
		int asDiscriminated(/* XXX @Nullable*/ ParserRuleAnalysis ruleAnalysis);

		/**
		 * Return the number of enumerationValue slot elements for an EnmeratedSlotAnalysis or throw an IllegalStateException otherwose.
		 */
		int asEnumerated(@NonNull EnumerationValue enumerationValue);

		/**
		 * Return true if this is a CountedSlotAnalysis.
		 */
		boolean isCounted();

		/**
		 * Return true if this is a DiscriminatedSlotAnalysis.
		 */
		boolean isDiscriminated();

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
		public int asDiscriminated(/* XXX @Nullable*/  ParserRuleAnalysis ruleAnalysis) {
			throw new IllegalStateException();
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
		public boolean isDiscriminated() {
			return false;
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
	 * A DiscriminatedSlotAnalysis provides the analysis of a (typically many)-valued EReference slot
	 * whose values may match some but not all possible rules applicable to their congtaining asignment.
	 */
	public static class DiscriminatedSlotAnalysis implements UserSlotAnalysis
	{
		private final @NonNull Map<@NonNull ParserRuleAnalysis, @NonNull Integer> ruleAnalysis2count = new HashMap<>();

		protected final int count;

		public DiscriminatedSlotAnalysis(int count) {
			this.count = count;
		}

		public void analyzeEReference(@NonNull UserElementAnalysis elementAnalysis, @NonNull Iterable<@NonNull ParserRuleAnalysis> ruleAnalyses) {
			UserSlotsAnalysis slotsAnalysis = elementAnalysis.getSlotsAnalysis();
			for (@NonNull ParserRuleAnalysis ruleAnalysis : ruleAnalyses) {
				DynamicRuleMatch dynamicRuleMatch = elementAnalysis.createDynamicRuleMatch(slotsAnalysis, ruleAnalysis);
				if (dynamicRuleMatch != null) {
					Integer oldCount = ruleAnalysis2count.get(ruleAnalysis);
					ruleAnalysis2count.put(ruleAnalysis, oldCount != null ? oldCount+1 : 1);
				}
			}
		}

		public @Nullable Integer basicGet(@NonNull ParserRuleAnalysis ruleAnalysis) {
			return ruleAnalysis2count.get(ruleAnalysis);
		}

		@Override
		public int asCounted() {
			throw new IllegalStateException();
		}

		@Override
		public int asDiscriminated(/* XXX @Nullable*/  ParserRuleAnalysis ruleAnalysis) {
			Integer value = ruleAnalysis2count.get(ruleAnalysis);
		//	return value != null ? value.intValue() : 0;			// XXX
			return count;
		}

		@Override
		public int asEnumerated(@NonNull EnumerationValue enumerationValue) {
			throw new IllegalStateException();
		}

		@Override
		public boolean isCounted() {
			return false;
		}

		@Override
		public boolean isDiscriminated() {
			return true;
		}

		@Override
		public boolean isEnumerated() {
			return false;
		}

	//	public void put(@NonNull ParserRuleAnalysis ruleAnalysis, int count) {
	//		ruleAnalysis2count.put(ruleAnalysis, count);
	//	}

		@Override
		public @NonNull String toString() {
			StringBuilder s = new StringBuilder();
			List<@NonNull ParserRuleAnalysis> keys  = new ArrayList<>(ruleAnalysis2count.keySet());
			Collections.sort(keys, NameUtil.NAMEABLE_COMPARATOR);
			boolean isFirst = true;
			for (@NonNull ParserRuleAnalysis key : keys) {
				if (!isFirst) {
					s.append(",");
				}
				s.append(key);
				s.append("=");
				s.append(ruleAnalysis2count.get(key));
				isFirst = false;
			}
			return s.toString();
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
		public int asDiscriminated(/* XXX @Nullable*/  ParserRuleAnalysis ruleAnalysis) {
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
		public boolean isDiscriminated() {
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
	protected final @Nullable Iterable<@NonNull SerializationRule> serializationRules;
	protected final @Nullable Map<@NonNull EReference, @NonNull List<@NonNull ParserRuleAnalysis>> eReference2disciminatedRuleAnalyses;
	private final @NonNull Map<@NonNull EStructuralFeature, @NonNull UserSlotAnalysis> eStructuralFeature2slotAnalysis;

	public UserSlotsAnalysis(@NonNull UserModelAnalysis modelAnalysis, @Nullable Iterable<@NonNull SerializationRule> serializationRules, @NonNull EObject eObject,
			@Nullable Map<@NonNull EReference, @NonNull List<@NonNull ParserRuleAnalysis>> eReference2disciminatedRuleAnalyses) {
		this.modelAnalysis = modelAnalysis;
		this.eObject = eObject;
		this.serializationRules = serializationRules;
		this.eReference2disciminatedRuleAnalyses = eReference2disciminatedRuleAnalyses;
		this.eStructuralFeature2slotAnalysis = analyze();
	}

	protected @NonNull Map<@NonNull EStructuralFeature, @NonNull UserSlotAnalysis> analyze() {
		Map<@NonNull EStructuralFeature, @NonNull UserSlotAnalysis> eStructuralFeature2slotAnalysis = new HashMap<>();
		Iterable<@NonNull SerializationRule> serializationRules2 = serializationRules;
		if (serializationRules2 != null) {
			EClass eClass = eObject.eClass();
			for (EStructuralFeature eFeature : eClass.getEAllStructuralFeatures()) {
				assert eFeature != null;
				if ("ownedArguments".equals(eFeature.getName())) {
					getClass();			// XXX debugging
				}
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
		UserSlotAnalysis slotAnalysis;
		Object object = eObject.eGet(eAttribute);
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
				slotAnalysis = CountedSlotAnalysis.valueOf(size);
			}
		}
		else if (object instanceof Boolean) {
			// NB Xtext has no ability to explicitly define a false Boolean.
			slotAnalysis = CountedSlotAnalysis.valueOf(object == Boolean.TRUE ? 1 : 0);
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
				slotAnalysis = CountedSlotAnalysis.valueOf(1);
			}
		}
		else if (eAttribute.isUnsettable()) {
			slotAnalysis = CountedSlotAnalysis.valueOf(0);
		}
		else {
			boolean allRulesNeedDefault = true;
			Iterable<@NonNull SerializationRule> serializationRules2 = serializationRules;
			assert serializationRules2 != null;
			for (@NonNull SerializationRule serializationRule : serializationRules2) {
				if (!serializationRule.getBasicSerializationRule().needsDefault(eAttribute)) {
					allRulesNeedDefault = false;
					break;
				}
			}
			slotAnalysis = CountedSlotAnalysis.valueOf(allRulesNeedDefault ? 1 : 0);
		}
		return slotAnalysis;
	}

	protected @Nullable UserSlotAnalysis analyzeEReference(@NonNull EReference eReference) {
		if (eReference.isContainer()) {
			return null;
		}
		Object object = eObject.eGet(eReference);
		if (eReference2disciminatedRuleAnalyses != null) {
			List<@NonNull ParserRuleAnalysis> ruleAnalyses = eReference2disciminatedRuleAnalyses.get(eReference);
			if (ruleAnalyses != null) {
				if (eReference.isMany()) {
					List<?> elements = (List<?>)object;
					DiscriminatedSlotAnalysis discriminatedSlotAnalysis = new DiscriminatedSlotAnalysis(elements.size());
					for (Object element : elements) {
						if (element != null) {			// null is not serializeable/parseable
							UserElementAnalysis elementAnalysis = modelAnalysis.getElementAnalysis((EObject)element);
						//	UserSlotsAnalysis slotsAnalysis = elementAnalysis.getSlotsAnalysis();
						//	elementAnalysis.createSerializer(this, targetRuleAnalysis)
							discriminatedSlotAnalysis.analyzeEReference(elementAnalysis, ruleAnalyses);
						}
					}
					return discriminatedSlotAnalysis;
				}
				else if (object != null){
					DiscriminatedSlotAnalysis discriminatedSlotAnalysis = new DiscriminatedSlotAnalysis(object != null ? 1 : 0);
					UserElementAnalysis elementAnalysis = modelAnalysis.getElementAnalysis((EObject)object);
					discriminatedSlotAnalysis.analyzeEReference(elementAnalysis, ruleAnalyses);
					return discriminatedSlotAnalysis;
				}
			}
		}
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
			int size = getSize(eStructuralFeature);
			s.append(String.format("%-30.30s%8d", eStructuralFeature.getName(), size));
			for (@NonNull SerializationRule serializationRule : serializationRules2) {
				BasicSerializationRule basicSerializationRule = serializationRule.getBasicSerializationRule();
				MultiplicativeCardinality multiplicativeCardinality = basicSerializationRule.getMultiplicativeCardinality(eStructuralFeature, NullEnumerationValue.INSTANCE);
				s.append(String.format("%4s", multiplicativeCardinality != null ? multiplicativeCardinality.toString() : "0"));
			}
			if (eStructuralFeature instanceof EAttribute) {
				EAttribute eAttribute = (EAttribute)eStructuralFeature;
				Iterable<@NonNull EnumerationValue> enumerationValues = getEnumerationValues(eAttribute);
				if (enumerationValues != null) {
					List<@NonNull EnumerationValue> sortedEnumerationValues = Lists.newArrayList(enumerationValues);
					Collections.sort(sortedEnumerationValues, NameUtil.NAMEABLE_COMPARATOR);
					for (@NonNull EnumerationValue enumerationValue : sortedEnumerationValues) {
						int size2 = getSize(eAttribute, enumerationValue);
						s.append(String.format("\n %-29.29s%8d", "'" + enumerationValue.getName() + "'", size2));
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

	public int getSize(@NonNull EStructuralFeature eStructuralFeature) {
		UserSlotAnalysis slotAnalysis = basicGetSlotAnalysis(eStructuralFeature);
		if (slotAnalysis == null) {
			return 0;
		}
		if (slotAnalysis.isCounted()) {
			return slotAnalysis.asCounted();
		}
		else if (slotAnalysis.isEnumerated()) {
			return slotAnalysis.asEnumerated(NullEnumerationValue.INSTANCE);
		}
		else if (slotAnalysis.isDiscriminated()) {
			return slotAnalysis.asDiscriminated(null);		// XXX
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
		else if (slotAnalysis.isDiscriminated()) {
			return slotAnalysis.asDiscriminated(null);		// XXX
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	public int getSize(@NonNull EReference eReference, @Nullable ParserRuleAnalysis ruleAnalysis) {
		UserSlotAnalysis slotAnalysis = basicGetSlotAnalysis(eReference);
		if (slotAnalysis == null) {
			return 0;
		}
		if (slotAnalysis.isCounted() && (ruleAnalysis == null)) {
			return slotAnalysis.asCounted();
		}
		/*else if (slotAnalysis.isEnumerated()) {
			return slotAnalysis.asEnumerated(enumerationValue);
		}
		else */if (slotAnalysis.isDiscriminated()) {
			return slotAnalysis.asDiscriminated(ruleAnalysis);		// XXX
		}
		else {
			throw new UnsupportedOperationException();
		}
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