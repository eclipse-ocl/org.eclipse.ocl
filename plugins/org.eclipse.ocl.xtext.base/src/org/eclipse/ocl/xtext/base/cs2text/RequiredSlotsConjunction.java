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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;

public class RequiredSlotsConjunction extends AbstractRequiredSlots
{
	private @NonNull Map<@NonNull EStructuralFeature, @NonNull Integer> eFeature2quantum = new HashMap<>();
	private @NonNull Map<@NonNull EStructuralFeature, @NonNull MultiplicativeCardinality> eFeature2multiplicativeCardinality = new HashMap<>();
	private @Nullable List<@NonNull SimpleRequiredSlot> conjunction = null;
	private @Nullable Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternatives2choice = null;
	private @Nullable List<@NonNull SerializationNode> serializedNodes = null;

	protected RequiredSlotsConjunction() {}

/*	public void accumulate(@NonNull AlternativesSerializationNode alternatives, @Nullable SerializationNode choice) {
		Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternatives2choice2 = alternatives2choice;
		if (alternatives2choice2 == null) {
			alternatives2choice2 = alternatives2choice = new HashMap<>();
		}
		boolean hadKey = alternatives2choice2.containsKey(alternatives);
		SerializationNode oldChoice = alternatives2choice2.put(alternatives, choice);
		assert !hadKey || (oldChoice == choice);
	} */

	public void accumulate(@NonNull RequiredSlotsConjunction innerConjunction, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		for (@NonNull SimpleRequiredSlot simpleRequiredSlot : innerConjunction.getConjunction()) {
			accumulate(simpleRequiredSlot, multiplicativeCardinality);
		}
	/*	Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternativesChoices = innerConjunction.getAlternativesChoices();
		if (alternativesChoices != null) {
			for (Entry<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> entry : alternativesChoices.entrySet()) {
				accumulate(entry.getKey(), entry.getValue());
			}
		} */
	//	getConjunction();		// XXX eager
	}

	public void accumulate(@NonNull SimpleRequiredSlot requiredSlot, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		EStructuralFeature eStructuralFeature = requiredSlot.getEStructuralFeature();
		if ("ownedProperties".equals(eStructuralFeature.getName())) {
			getClass();	// XXX
		}
		boolean newMayBeMany = requiredSlot.getMultiplicativeCardinality().mayBeMany();
		boolean newMayBeZero = requiredSlot.getMultiplicativeCardinality().mayBeZero();
		if (multiplicativeCardinality.mayBeZero()) {
			newMayBeZero = true;
		}
		if (multiplicativeCardinality.mayBeMany()) {
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


		int quantum = 1;		// XXX
		Integer oldQuantum = eFeature2quantum.get(eStructuralFeature);
		int newQuantum = oldQuantum != null ? oldQuantum.intValue() : 0;
		newQuantum += quantum;
	//	assert newQuantum == 1;
		eFeature2quantum.put(eStructuralFeature, newQuantum);



		MultiplicativeCardinality newMultiplicativeCardinality = newMayBeMany
					? newMayBeZero ? MultiplicativeCardinality.ZERO_OR_MORE : MultiplicativeCardinality.ONE_OR_MORE
					: newMayBeZero ? MultiplicativeCardinality.ZERO_OR_ONE : MultiplicativeCardinality.ONE;
		eFeature2multiplicativeCardinality.put(eStructuralFeature, newMultiplicativeCardinality);
	}

	public @Nullable Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> getAlternativesChoices() {
		return alternatives2choice;
	}

	@Override
	public @NonNull Iterable<@NonNull SimpleRequiredSlot> getConjunction() {
		List<@NonNull SimpleRequiredSlot> conjunction = this.conjunction;
		if (conjunction == null) {
			this.conjunction = conjunction = new ArrayList<>();
			List<@NonNull EStructuralFeature> features = new ArrayList<>(eFeature2multiplicativeCardinality.keySet());
			Collections.sort(features, NameUtil.ENAMED_ELEMENT_COMPARATOR);
			for (@NonNull EStructuralFeature eStructuralFeature : features) {
				MultiplicativeCardinality multiplicativeCardinality = eFeature2multiplicativeCardinality.get(eStructuralFeature);
				assert multiplicativeCardinality != null;
				EClass eFeatureScope = XtextGrammarUtil.getEContainingClass(eStructuralFeature);		// XXX do we need scope ??
				conjunction.add(new SimpleRequiredSlot(eFeatureScope, eStructuralFeature, multiplicativeCardinality));
			}
		}
		return conjunction;
	}

	@Override
	public @NonNull RequiredSlotsConjunction getConjunction(int conjunctionIndex) {
		assert conjunctionIndex == 0;
		return this;
	}

	@Override
	public int getConjunctionCount() {
		return 1;
	}

//	@Override
//	public @NonNull Iterable<@NonNull SimpleRequiredSlot> getConjunctionTerms(int conjunctionIndex) {
//		return getConjunction();
//	}

	@Override
	public @NonNull Iterable<@NonNull RequiredSlotsConjunction> getDisjunction() {
		return Collections.singletonList(this);
	}

	@Override
	public @NonNull Iterable<@NonNull EStructuralFeature> getEStructuralFeatures() {
		return eFeature2multiplicativeCardinality.keySet();
	}

	@Override
	public int getLowerBound(@NonNull EStructuralFeature eStructuralFeature) {
		MultiplicativeCardinality multiplicativeCardinality = eFeature2multiplicativeCardinality.get(eStructuralFeature);
		return (multiplicativeCardinality == null) || multiplicativeCardinality.mayBeZero() ? 0 : 1;
	}

	@Override
	public int getQuantum(@NonNull EStructuralFeature eStructuralFeature) {
//		MultiplicativeCardinality multiplicativeCardinality = eFeature2multiplicativeCardinality.get(eStructuralFeature);
//		Integer quantum = eFeature2quantum.get(eStructuralFeature);
//		return multiplicativeCardinality != null ? 1/*quantum.intValue()*/ : 0;
		Integer quantum = eFeature2quantum.get(eStructuralFeature);
		return quantum != null ? quantum.intValue() : 0;
	}

	public @NonNull List<@NonNull SerializationNode> getSerializedNodes() {
		assert serializedNodes != null;
		return serializedNodes;
	}

	public @NonNull Iterable<@NonNull SimpleRequiredSlot> getTerms() {
		return getConjunction();
	}

	@Override
	public int getUpperBound(@NonNull EStructuralFeature eStructuralFeature) {
		MultiplicativeCardinality multiplicativeCardinality = eFeature2multiplicativeCardinality.get(eStructuralFeature);
		return (multiplicativeCardinality != null) && multiplicativeCardinality.mayBeMany() ? -1 : 0;
	}

	public void preSerialize(@NonNull SerializationNode serializationNode) {
	//	assert this.serializedNodes == null;
		List<@NonNull SerializationNode> serializedNodes = new ArrayList<>();
		Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternatives2choice2 = alternatives2choice;
	//	if ((alternatives2choice2 == null) && (serializationNode instanceof AlternativesSerializationNode)) {
	//		alternatives2choice2 = new HashMap<>();
	//		alternatives2choice2.put((AlternativesSerializationNode)serializationNode, null);		// XXX
	//	}
	//	else {
			serializationNode.preSerialize(serializedNodes, alternatives2choice2);
			this.serializedNodes = serializedNodes;
	//	}
	}

	public void setAlternatives(@NonNull Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternatives2choice) {
		assert this.alternatives2choice == null;
		this.alternatives2choice = alternatives2choice;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
	//	List<@NonNull SimpleRequiredSlot> conjunction = this.conjunction;
	//	if (conjunction != null) {
		boolean isFirst = true;
		for (@NonNull SimpleRequiredSlot requiredSlot : getConjunction()) {	// XXX lazy
			if (!isFirst) {
				s.append(" & ");
			}
			requiredSlot.toString(s, depth);
			isFirst = false;
		}
		List<@NonNull SerializationNode> serializedNodes2 = serializedNodes;
		if (serializedNodes2 != null) {
			s.append("\n");
			StringUtil.appendIndentation(s, depth+1, "\t");
			for (@NonNull SerializationNode serializedNode : serializedNodes2) {
				serializedNode.toString(s, depth+2);
			}
		}
	//	}
	}
}