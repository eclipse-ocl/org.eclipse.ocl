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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public class SerializationRule
{
	protected final @NonNull XtextParserRuleAnalysis ruleAnalysis;
	protected final @NonNull SerializationNode rootSerializationNode;
	private final @NonNull Map<@NonNull EStructuralFeature, @NonNull MultiplicativeCardinality> eFeature2multiplicativeCardinality = new HashMap<>();
	private final @NonNull List<@NonNull AssignedSerializationNode> assignedSerializationNodes = new ArrayList<>();
//	private @Nullable List<@NonNull AssignedSerializationNode> conjunction = null;
//	private @Nullable Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternatives2choice = null;
	private @Nullable PreSerializer preSerializer = null;
	private @Nullable EClass producedEClass = null;

	public SerializationRule(@NonNull XtextParserRuleAnalysis ruleAnalysis, @NonNull SerializationNode rootSerializationNode) {
		this.ruleAnalysis = ruleAnalysis;
		this.rootSerializationNode = rootSerializationNode;
	}

	public void accumulate(@NonNull SerializationRule innerConjunction, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		for (@NonNull AssignedSerializationNode assignedSerializationNode : innerConjunction.getConjunction()) {
			accumulate(assignedSerializationNode, assignedSerializationNode.getMultiplicativeCardinality(), multiplicativeCardinality);
		}
	}

	public void accumulate(@NonNull List<@NonNull SerializationNode> list, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		for (@NonNull SerializationNode assignedSerializationNode : list) {
			if (assignedSerializationNode instanceof AssignedSerializationNode) {		// XXX bad cast
				accumulate((AssignedSerializationNode) assignedSerializationNode, assignedSerializationNode.getMultiplicativeCardinality(), multiplicativeCardinality);
			}
		}
	}

	public void accumulate(@NonNull AssignedSerializationNode serializationNode, @NonNull MultiplicativeCardinality innerMultiplicativeCardinality, @NonNull MultiplicativeCardinality outerMultiplicativeCardinality) {
//		assert innerMultiplicativeCardinality == assignmentAnalysis.getMultiplicativeCardinality();
		XtextAssignmentAnalysis assignmentAnalysis = serializationNode.getAssignmentAnalysis();
		EStructuralFeature eStructuralFeature = assignmentAnalysis.getEStructuralFeature();
		if ("ownedProperties".equals(eStructuralFeature.getName())) {
			getClass();	// XXX
		}
		boolean newMayBeMany = innerMultiplicativeCardinality.mayBeMany();
		boolean newMayBeZero = innerMultiplicativeCardinality.mayBeZero();
		if (outerMultiplicativeCardinality.mayBeZero()) {
			newMayBeZero = true;
		}
		if (outerMultiplicativeCardinality.mayBeMany()) {
			newMayBeMany = true;
		}
		MultiplicativeCardinality oldMultiplicativeCardinality = eFeature2multiplicativeCardinality.get(eStructuralFeature);
		if (oldMultiplicativeCardinality != null) {
			boolean oldMayBeMany = oldMultiplicativeCardinality.mayBeMany();
			boolean oldMayBeZero = oldMultiplicativeCardinality.mayBeZero();
			if (!oldMayBeZero) {
				newMayBeZero = false;
			}
			if (oldMayBeMany) {
				newMayBeMany = true;
			}
		}
		MultiplicativeCardinality newMultiplicativeCardinality = newMayBeMany
				? newMayBeZero ? MultiplicativeCardinality.ZERO_OR_MORE : MultiplicativeCardinality.ONE_OR_MORE
				: newMayBeZero ? MultiplicativeCardinality.ZERO_OR_ONE : MultiplicativeCardinality.ONE;
		eFeature2multiplicativeCardinality.put(eStructuralFeature, newMultiplicativeCardinality);
		assignedSerializationNodes.add(serializationNode);
	}

	public Map<@NonNull CardinalityVariable, @NonNull Integer> computeActualCardinalities(@NonNull EObject element,
			@NonNull Map<@NonNull EStructuralFeature, @NonNull Object> eFeature2contentAnalysis) {
		PreSerializer preSerializer = getPreSerializer();
		return preSerializer.computeActualCardinalities(element, eFeature2contentAnalysis);
	}

//	public @Nullable Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> getAlternativesChoices() {
//		return alternatives2choice;
//	}

	public @NonNull Iterable<@NonNull AssignedSerializationNode> getConjunction() {
		return assignedSerializationNodes;
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
		return eFeature2multiplicativeCardinality.keySet();
	}

	public @Nullable MultiplicativeCardinality getMultiplicativeCardinality(@NonNull EStructuralFeature eStructuralFeature) {
		return eFeature2multiplicativeCardinality.get(eStructuralFeature);
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

//	public void setAlternatives(@NonNull Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternatives2choice) {
//		assert this.alternatives2choice == null;
//		this.alternatives2choice = alternatives2choice;
//	}

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
		//	List<@NonNull SimpleRequiredSlot> conjunction = this.conjunction;
		//	if (conjunction != null) {
		boolean isFirst = true;
		for (@NonNull AssignedSerializationNode requiredSlot : getConjunction()) {	// XXX lazy
			if (!isFirst) {
				s.append(" & ");
			}
			requiredSlot.toString(s, depth);
			isFirst = false;
		}
		if (preSerializer != null) {
			preSerializer.toString(s, depth);
		}
	}
}