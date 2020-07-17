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
package org.eclipse.ocl.xtext.base.cs2text;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SequenceSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;

public class SerializationRule
{
	protected final @NonNull ParserRuleAnalysis ruleAnalysis;
	protected final @NonNull SerializationNode rootSerializationNode;
	private final @NonNull Map<@NonNull EStructuralFeature, @NonNull Map<@NonNull EnumerationValue, @NonNull MultiplicativeCardinality>> eFeature2enumerationValue2multiplicativeCardinality = new HashMap<>();
//	private final @NonNull List<@NonNull AssignedSerializationNode> assignedSerializationNodes = new ArrayList<>();
	private @Nullable PreSerializer preSerializer = null;
	private @Nullable EClass producedEClass = null;

	public SerializationRule(@NonNull ParserRuleAnalysis ruleAnalysis, @NonNull SerializationNode rootSerializationNode) {
		this.ruleAnalysis = ruleAnalysis;
		this.rootSerializationNode = rootSerializationNode;
		if ("EnumerationCS".equals(ruleAnalysis.getRuleName())) {
			getClass();	// XXX
		}
		accumulate(rootSerializationNode, MultiplicativeCardinality.ONE);
	}

	private void accumulate(@NonNull SerializationNode serializationNode, @NonNull MultiplicativeCardinality outerMultiplicativeCardinality) {
		MultiplicativeCardinality innerMultiplicativeCardinality = serializationNode.getMultiplicativeCardinality();
		MultiplicativeCardinality netMultiplicativeCardinality = MultiplicativeCardinality.max(innerMultiplicativeCardinality, outerMultiplicativeCardinality);
		if (serializationNode instanceof AssignedSerializationNode) {		// XXX bad cast
			AssignedSerializationNode assignedSerializationNode = (AssignedSerializationNode)serializationNode;
			EnumerationValue enumerationValue = assignedSerializationNode.getEnumerationValue();
			AssignmentAnalysis assignmentAnalysis = assignedSerializationNode.getAssignmentAnalysis();
			EStructuralFeature eStructuralFeature = assignmentAnalysis.getEStructuralFeature();
			if ("ownedProperties".equals(eStructuralFeature.getName())) {
				getClass();	// XXX
			}
			MultiplicativeCardinality newMultiplicativeCardinality = netMultiplicativeCardinality;
			Map<@NonNull EnumerationValue, @NonNull MultiplicativeCardinality> enumerationValue2multiplicativeCardinality = eFeature2enumerationValue2multiplicativeCardinality.get(eStructuralFeature);
			if (enumerationValue2multiplicativeCardinality == null) {
				enumerationValue2multiplicativeCardinality = new HashMap<>();
				eFeature2enumerationValue2multiplicativeCardinality.put(eStructuralFeature, enumerationValue2multiplicativeCardinality);
			}
			MultiplicativeCardinality oldMultiplicativeCardinality = enumerationValue2multiplicativeCardinality.get(enumerationValue);
			if (oldMultiplicativeCardinality != null) {
				boolean newMayBeMany = netMultiplicativeCardinality.mayBeMany();
				boolean newMayBeZero = netMultiplicativeCardinality.mayBeZero();
				boolean oldMayBeMany = oldMultiplicativeCardinality.mayBeMany();
				boolean oldMayBeZero = oldMultiplicativeCardinality.mayBeZero();
				if (!oldMayBeZero) {
					newMayBeZero = false;
				}
				if (oldMayBeMany) {
					newMayBeMany = true;
				}
				newMultiplicativeCardinality = newMayBeMany
					? newMayBeZero ? MultiplicativeCardinality.ZERO_OR_MORE : MultiplicativeCardinality.ONE_OR_MORE
					: newMayBeZero ? MultiplicativeCardinality.ZERO_OR_ONE : MultiplicativeCardinality.ONE;
			}
			enumerationValue2multiplicativeCardinality.put(enumerationValue, newMultiplicativeCardinality);
//			assignedSerializationNodes.add(assignedSerializationNode);
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			SequenceSerializationNode sequenceSerializationNode = (SequenceSerializationNode)serializationNode;
			for (@NonNull SerializationNode nestedSerializationNode : sequenceSerializationNode.getSerializationNodes()) {
				accumulate(nestedSerializationNode, netMultiplicativeCardinality);
			}
		}
	}

	public Map<@NonNull CardinalityVariable, @NonNull Integer> computeActualCardinalities(@NonNull EObject element,
			@NonNull Map<@NonNull EStructuralFeature, @NonNull Object> eFeature2contentAnalysis) {
		PreSerializer preSerializer = getPreSerializer();
		return preSerializer.computeActualCardinalities(element, eFeature2contentAnalysis);
	}

	public @NonNull EClass getProducedEClass() {
		EClass producedEClass2 = producedEClass;
		if (producedEClass2  == null) {
			producedEClass2 = getProducedEClass(getSerializedNodes());
			if (producedEClass2 == null) {
				producedEClass2 = ruleAnalysis.getReturnedEClass();
			}
			producedEClass = producedEClass2;
		}
		return producedEClass2;
	}

	private @Nullable EClass getProducedEClass(@NonNull List<@NonNull SerializationNode> serializedNodes) {
		EClass producedEClass = null;
		for (@NonNull SerializationNode serializationNode : serializedNodes) {
			EClass nestedEClass = null;
			if (serializationNode instanceof AssignedSerializationNode) {
				nestedEClass = ((AssignedSerializationNode)serializationNode).getEFeatureScope();
			}
			else if (serializationNode instanceof SequenceSerializationNode) {
				nestedEClass = getProducedEClass(((SequenceSerializationNode)serializationNode).getSerializationNodes());
			}
			if (nestedEClass != null) {
				if ((producedEClass == null) || producedEClass.isSuperTypeOf(nestedEClass)) {
					producedEClass = nestedEClass;
				}
				else {
					assert nestedEClass.isSuperTypeOf(producedEClass);
				}
			}
		}
		return producedEClass;
	}

	public @NonNull Iterable<@NonNull EStructuralFeature> getEStructuralFeatures() {
		return eFeature2enumerationValue2multiplicativeCardinality.keySet();
	}

	public @Nullable MultiplicativeCardinality getMultiplicativeCardinality(@NonNull EStructuralFeature eStructuralFeature, @NonNull EnumerationValue enumerationValue) {
		Map<@NonNull EnumerationValue, @NonNull MultiplicativeCardinality> enumerationValue2multiplicativeCardinality = eFeature2enumerationValue2multiplicativeCardinality.get(eStructuralFeature);
		if (enumerationValue2multiplicativeCardinality == null) {
			return null;
		}
		return enumerationValue2multiplicativeCardinality.get(enumerationValue);
	}

	public @NonNull String getName() {
		return ruleAnalysis.getName();
	}

	public @NonNull PreSerializer getPreSerializer() {
		if (preSerializer == null) {
			PreSerializer preSerializer2 = new PreSerializer(ruleAnalysis, this, rootSerializationNode);
			this.preSerializer = preSerializer2;
			preSerializer2.preSerialize();
		}
		assert preSerializer != null;
		return preSerializer;
	}

	public @NonNull List<@NonNull SerializationNode> getSerializedNodes() {
		PreSerializer preSerializer = getPreSerializer();
		return preSerializer.getSerializedNodes();
	}

	public @NonNull List<@NonNull SerializationRule> getSerializationRules() {
		return Collections.singletonList(this);
	}

	public void toRuleString(@NonNull StringBuilder s) {
		assert preSerializer != null;
		preSerializer.toRuleString(s);
	}

	@Override
	public final @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return String.valueOf(s);
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		rootSerializationNode.toString(s, depth);
	/*	boolean isFirst = true;
		for (@NonNull AssignedSerializationNode assignedSerializationNode : assignedSerializationNodes) {
			if (!isFirst) {
				s.append(" & ");
			}
			assignedSerializationNode.toString(s, depth);
			isFirst = false;
		} */
		if (preSerializer != null) {
			preSerializer.toString(s, depth);
		}
	}
}