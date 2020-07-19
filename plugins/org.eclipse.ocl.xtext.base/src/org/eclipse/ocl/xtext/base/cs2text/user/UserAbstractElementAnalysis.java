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
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.xtext.base.cs2text.CardinalityExpression;
import org.eclipse.ocl.xtext.base.cs2text.CardinalityVariable;
import org.eclipse.ocl.xtext.base.cs2text.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.Serializer;
import org.eclipse.ocl.xtext.base.cs2text.elements.AlternativeAssignedKeywordsSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedKeywordSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SequenceSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.NullEnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * An UserAbstractElementAnalysis provides the working context to assist in the determination of the Xtext grammar rule
 * that can produce and assign a user model element.
 */
public abstract class UserAbstractElementAnalysis implements Nameable
{
	private static int count = 0;

	protected final @NonNull UserModelAnalysis modelAnalysis;
	protected final @NonNull GrammarAnalysis grammarAnalysis;
	protected final @NonNull EObject element;
	private final @NonNull String name;
	private @Nullable Iterable<@NonNull SerializationRule> serializationRules = null;

	public UserAbstractElementAnalysis(@NonNull UserModelAnalysis modelAnalysis, @NonNull EObject element) {
		this.modelAnalysis = modelAnalysis;
		this.grammarAnalysis = modelAnalysis.getGrammarAnalysis();
		this.element = element;
		this.name = element.eClass().getName() + "@" + ++count;
	}

	public @Nullable Serializer createSerializer(@NonNull Map<@NonNull EStructuralFeature, @NonNull Object> eFeature2contentAnalysis) {
		Iterable<@NonNull SerializationRule> serializationRules = getSerializationRules();
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			Map<@NonNull CardinalityVariable, @NonNull Integer> variable2value = serializationRule.computeActualCardinalities(element, eFeature2contentAnalysis);
			if (variable2value != null) {
				return new Serializer(serializationRule, modelAnalysis, element, variable2value);
			}
		}
		return null;
	}

	public void diagnose(@NonNull StringBuilder s, @NonNull Map<@NonNull EStructuralFeature, @NonNull Object> eFeature2contentAnalysis) {
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
		List<@NonNull EStructuralFeature> sortedFeatures = new ArrayList<>(eFeature2contentAnalysis.keySet());
		Collections.sort(sortedFeatures, NameUtil.ENAMED_ELEMENT_COMPARATOR);
		for (@NonNull EStructuralFeature eStructuralFeature : sortedFeatures) {
			s.append("\n");
			int size = CardinalityExpression.getSize(eFeature2contentAnalysis, eStructuralFeature, NullEnumerationValue.INSTANCE);
			s.append(String.format("%-30.30s%8d", eStructuralFeature.getName(), size));
			for (@NonNull SerializationRule serializationRule : serializationRules2) {
				MultiplicativeCardinality multiplicativeCardinality = serializationRule.getMultiplicativeCardinality(eStructuralFeature, NullEnumerationValue.INSTANCE);
				s.append(String.format("%4s", multiplicativeCardinality != null ? multiplicativeCardinality.toString() : "0"));
			}
			if (eStructuralFeature instanceof EAttribute) {
				Iterable<@NonNull EnumerationValue> enumerationValues = getEnumerationValues((EAttribute)eStructuralFeature);
				if (enumerationValues != null) {
					List<@NonNull EnumerationValue> sortedEnumerationValues = Lists.newArrayList(enumerationValues);
					Collections.sort(sortedEnumerationValues, NameUtil.NAMEABLE_COMPARATOR);
					for (@NonNull EnumerationValue enumerationValue : sortedEnumerationValues) {
						int size2 = CardinalityExpression.getSize(eFeature2contentAnalysis, eStructuralFeature, enumerationValue);
						s.append(String.format("\n %-29.29s%8d", "\"" + enumerationValue.getName() + "\"", size2));
						for (@NonNull SerializationRule serializationRule : serializationRules2) {
							MultiplicativeCardinality multiplicativeCardinality = serializationRule.getMultiplicativeCardinality(eStructuralFeature, enumerationValue);
							s.append(String.format("%4s", multiplicativeCardinality != null ? multiplicativeCardinality.toString() : "0"));
						}
					}
				}
			}
		}
		s.append("\n");
	}

	public abstract @Nullable UserAbstractElementAnalysis getContainingElementAnalysis();

	protected @NonNull Map<@NonNull EStructuralFeature, @NonNull Object> getContentAnalysis() {
		Map<@NonNull EStructuralFeature, @NonNull Object> eFeature2contentAnalysis = new HashMap<>();
		EClass eClass = element.eClass();
		for (EStructuralFeature eFeature : eClass.getEAllStructuralFeatures()) {
			assert eFeature != null;
			if ("lowerBound".equals(eFeature.getName())) {
				getClass();			// XXX debugging
			}
			if (!eFeature.isDerived() && !eFeature.isTransient() && !eFeature.isVolatile() && (!(eFeature instanceof EReference) || !((EReference)eFeature).isContainer())) {
				Object contentAnalysis = null;
				Object object = element.eGet(eFeature);
				Iterable<@NonNull EnumerationValue> enumerationValues = eFeature instanceof EAttribute ? getEnumerationValues((EAttribute)eFeature) : null;
				if (eFeature.isMany()) {
					List<?> elements = (List<?>)object;
					int size = elements.size();
					if ((size > 0) && (eFeature instanceof EAttribute) && (enumerationValues != null)) {
						Map<@NonNull EnumerationValue, @NonNull Integer> enumerationValue2count = new HashMap<>();
						int others = 0;
						for (Object element : elements) {
							String string = String.valueOf(element);
							boolean gotOne = false;
							for (@NonNull EnumerationValue enumerationValue : enumerationValues) {
								if (enumerationValue.isElement(string)) {
									Integer count = enumerationValue2count.get(enumerationValue);
									enumerationValue2count.put(enumerationValue, (count == null ? 0 : count.intValue()) + 1);
									gotOne = true;
								}
							}
							if (!gotOne) {
								others++;
							}
						}
						enumerationValue2count.put(NullEnumerationValue.INSTANCE, others);
						contentAnalysis = enumerationValue2count;
					}
					else {
						contentAnalysis = size;
					}
				}
				else if (!eFeature.isUnsettable() || element.eIsSet(eFeature)) {
					boolean eIsSet = element.eIsSet(eFeature);
					if (eFeature instanceof EReference) {
						assert object != null == eIsSet;
						contentAnalysis = object != null ? 1 : 0;
					}
					else /*if (eFeature.getEType().getInstanceClass() == boolean.class) {
						assert (object == Boolean.TRUE) == eIsSet;
						contentAnalysis = object == Boolean.TRUE ? 1 : 0;
					}
					else*/ if (enumerationValues == null) {
						contentAnalysis = eIsSet || !eFeature.isUnsettable() ? 1 : 0;
					}
					else {
						String string = String.valueOf(object);
						Map<@NonNull EnumerationValue, @NonNull Integer> enumerationValue2count = new HashMap<>();
						for (@NonNull EnumerationValue enumerationValue : enumerationValues) {
							if (enumerationValue.isElement(string)) {
								Integer count = enumerationValue2count.get(enumerationValue);
								enumerationValue2count.put(enumerationValue, (count == null ? 0 : count.intValue()) + 1);
							}
						}
						contentAnalysis = enumerationValue2count;
					}
				}
				else {
					contentAnalysis = 0;
				}
				eFeature2contentAnalysis.put(eFeature, contentAnalysis);
			}
		}
		return eFeature2contentAnalysis;
	}

	public @NonNull EObject getElement() {
		return element;
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

	public @NonNull GrammarAnalysis getGrammarAnalysis() {
		return grammarAnalysis;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	public @NonNull Iterable<@NonNull SerializationRule> getSerializationRules() {
		EClass eClass = UserModelAnalysis.eClass(element);
		if ("TopLevelCS".equals(eClass.getName())) {
			getClass(); // XXX
		}
		if (serializationRules == null) {
			serializationRules = grammarAnalysis.getSerializationRules(eClass);
		}
		assert serializationRules != null;
		return serializationRules;
	}

	protected abstract boolean hasCompatibleContainmentHierarchy();

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		s.append(getName());
		s.append(" <=>");
		Iterable<@NonNull SerializationRule> serializationRules2 = serializationRules; //getSerializationRules();
		if (serializationRules2 != null) {
			boolean isMany = Iterables.size(serializationRules2) > 1;
			for (@NonNull SerializationRule serializationRule : serializationRules2) {
				if (isMany) {
					s.append("\n\t\t");
				}
				else {
					s.append(" ");
				}
				s.append(serializationRule.getName());
				s.append(" - ");
				serializationRule.toRuleString(s);
			}
		}
		return s.toString();
	}
}