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
import org.eclipse.ocl.pivot.utilities.NameUtil;

public class SerializationRule extends AbstractRequiredSlots
{
//	private @NonNull Map<@NonNull EStructuralFeature, @NonNull Integer> eFeature2quantum = new HashMap<>();
	private @NonNull Map<@NonNull EStructuralFeature, @NonNull MultiplicativeCardinality> eFeature2multiplicativeCardinality = new HashMap<>();
	private @Nullable List<@NonNull RequiredSlots> conjunction = null;
	private @Nullable Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternatives2choice = null;
	private @Nullable PreSerializer preSerializer = null;
	private @Nullable EClass producedEClass = null;

	public SerializationRule(@NonNull XtextParserRuleAnalysis ruleAnalysis) {
		super(ruleAnalysis);
	}

	/*	public void accumulate(@NonNull AlternativesSerializationNode alternatives, @Nullable SerializationNode choice) {
		Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternatives2choice2 = alternatives2choice;
		if (alternatives2choice2 == null) {
			alternatives2choice2 = alternatives2choice = new HashMap<>();
		}
		boolean hadKey = alternatives2choice2.containsKey(alternatives);
		SerializationNode oldChoice = alternatives2choice2.put(alternatives, choice);
		assert !hadKey || (oldChoice == choice);
	} */

	public void accumulate(@NonNull SerializationRule innerConjunction, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		for (@NonNull RequiredSlots requiredSlots : innerConjunction.getConjunction()) {
			if (requiredSlots instanceof SerializationRule) {
				accumulate((SerializationRule)requiredSlots, multiplicativeCardinality);
			}
			else {
				accumulate((SimpleRequiredSlot)requiredSlots, multiplicativeCardinality);
			}
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


	//	int quantum = 1;		// XXX
	//	Integer oldQuantum = eFeature2quantum.get(eStructuralFeature);
	//	int newQuantum = oldQuantum != null ? oldQuantum.intValue() : 0;
	//	newQuantum += quantum;
		//	assert newQuantum == 1;
	//	eFeature2quantum.put(eStructuralFeature, newQuantum);



		MultiplicativeCardinality newMultiplicativeCardinality = newMayBeMany
				? newMayBeZero ? MultiplicativeCardinality.ZERO_OR_MORE : MultiplicativeCardinality.ONE_OR_MORE
					: newMayBeZero ? MultiplicativeCardinality.ZERO_OR_ONE : MultiplicativeCardinality.ONE;
		eFeature2multiplicativeCardinality.put(eStructuralFeature, newMultiplicativeCardinality);
	}

	public Map<@NonNull CardinalityVariable, @NonNull Integer> computeActualCardinalities(@NonNull EObject element,
			@NonNull Map<@NonNull EStructuralFeature, @NonNull Object> eFeature2contentAnalysis) {
		PreSerializer preSerializer = getPreSerializer();
		return preSerializer.computeActualCardinalities(element, eFeature2contentAnalysis);
	}

	public @Nullable Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> getAlternativesChoices() {
		return alternatives2choice;
	}

	@Override
	public @NonNull Iterable<@NonNull RequiredSlots> getConjunction() {
		List<@NonNull RequiredSlots> conjunction = this.conjunction;
		if (conjunction == null) {
			this.conjunction = conjunction = new ArrayList<>();
			List<@NonNull EStructuralFeature> features = new ArrayList<>(eFeature2multiplicativeCardinality.keySet());
			Collections.sort(features, NameUtil.ENAMED_ELEMENT_COMPARATOR);
			for (@NonNull EStructuralFeature eStructuralFeature : features) {
				MultiplicativeCardinality multiplicativeCardinality = eFeature2multiplicativeCardinality.get(eStructuralFeature);
				assert multiplicativeCardinality != null;
				EClass eFeatureScope = XtextGrammarUtil.getEContainingClass(eStructuralFeature);		// XXX do we need scope ??
				conjunction.add(new SimpleRequiredSlot(ruleAnalysis, eFeatureScope, eStructuralFeature, multiplicativeCardinality));
			}
		}
		return conjunction;
	}

	//	@Override
	//	public @NonNull Iterable<@NonNull SimpleRequiredSlot> getConjunctionTerms(int conjunctionIndex) {
	//		return getConjunction();
	//	}

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
			PreSerializer preSerializer2 = new PreSerializer(ruleAnalysis, this, ruleAnalysis.getRootSerializationNode());
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

	//	@Override
	//	public @NonNull Iterable<@NonNull SimpleRequiredSlot> getConjunctionTerms(int conjunctionIndex) {
	//		return getConjunction();
	//	}

	@Override
	public @NonNull List<@NonNull SerializationRule> getSerializationRules() {
		return Collections.singletonList(this);
	}

	public @NonNull Iterable<@NonNull RequiredSlots> getTerms() {
		return getConjunction();
	}

/*	public void preSerialize(@NonNull XtextParserRuleAnalysis ruleAnalysis, @NonNull SerializationNode rootSerializationNode) {
		assert ruleAnalysis == this.ruleAnalysis;
		assert rootSerializationNode == ruleAnalysis.getRootSerializationNode();
		PreSerializer preSerializer2 = new PreSerializer(ruleAnalysis, this, rootSerializationNode);
		this.preSerializer = preSerializer2;
		preSerializer2.preSerialize();
	} */

	public void setAlternatives(@NonNull Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternatives2choice) {
		assert this.alternatives2choice == null;
		this.alternatives2choice = alternatives2choice;
	}

	public void toRuleString(@NonNull StringBuilder s) {
		assert preSerializer != null;
		preSerializer.toRuleString(s);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		//	List<@NonNull SimpleRequiredSlot> conjunction = this.conjunction;
		//	if (conjunction != null) {
		boolean isFirst = true;
		for (@NonNull RequiredSlots requiredSlot : getConjunction()) {	// XXX lazy
			if (!isFirst) {
				s.append(" & ");
			}
			if (requiredSlot instanceof SerializationRule) {
				s.append("{");
			}
			requiredSlot.toString(s, depth);
			if (requiredSlot instanceof SerializationRule) {
				s.append("}");
			}
			isFirst = false;
		}
		if (preSerializer != null) {
			preSerializer.toString(s, depth > 0 ? depth+1 : -1);
		}
	}
}