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
package org.eclipse.ocl.examples.xtext.serializer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.EReference_RuleIndexes;

public class EClassValue implements Nameable
{
	protected final @NonNull EClass eClass;
	protected final @NonNull SerializationRule @NonNull [] serializationRules;
	protected final @NonNull EReference_RuleIndexes @Nullable [] eReferenceRuleIndexes;

	public EClassValue(/*@NonNull*/ EClass eClass, @NonNull SerializationRule @NonNull [] serializationRules,
			@NonNull EReference_RuleIndexes @Nullable [] eReferenceRuleIndexes) {
		assert eClass != null;
		this.eClass = eClass;
		this.serializationRules = serializationRules;
		this.eReferenceRuleIndexes = eReferenceRuleIndexes;
	}

	public @NonNull EReference_RuleIndexes @Nullable [] basicGetEReferenceRuleIndexes() {
		return eReferenceRuleIndexes;
	}

	public @NonNull DynamicSerializationRules createDynamicSerializationRules(@Nullable GrammarRuleVector targetRuleValueIndexes) {
		if (targetRuleValueIndexes == null)  {
			return new DynamicSerializationRules(this, serializationRules);
		}
		List<@NonNull SerializationRule> newSerializationRules = new ArrayList<>();
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			int ruleValueIndex = serializationRule.getRuleValueIndex();
			if (targetRuleValueIndexes.test(ruleValueIndex)) {
				newSerializationRules.add(serializationRule);
			}
		}
		@NonNull SerializationRule @NonNull [] newEmptyArray = new @NonNull SerializationRule @NonNull [newSerializationRules.size()];
		@SuppressWarnings("null")
		@NonNull SerializationRule @NonNull [] newFullArray = newSerializationRules.toArray(newEmptyArray);
		return new DynamicSerializationRules(this, newFullArray);
	}

	/**
	 * Return the rule analyses assigned by one or more of the serialization rules that can assign eContainmentFeature.
	 */
	public @Nullable GrammarRuleVector getAssignedTargetRuleValues(@NonNull EReference eContainmentFeature) {
	/*	Set<@NonNull GrammarRuleValue> targetRuleValues = new HashSet<>();
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			Iterable<@NonNull AssignedSerializationNode> assignedSerializationNodes = serializationRule.getAssignedSerializationNodes(eContainmentFeature);
			if (assignedSerializationNodes != null) {
				for (@NonNull AssignedSerializationNode assignedSerializationNode : assignedSerializationNodes) {
					for (@NonNull AbstractRuleAnalysis targetRuleAnalysis : assignedSerializationNode.getAssignmentAnalysis().getTargetRuleAnalyses()) {
						targetRuleValues.add(targetRuleAnalysis.getRuleValue());
					}
				}
			}
		}
		return targetRuleValues; */
		if (eReferenceRuleIndexes != null) {
			for (@NonNull EReference_RuleIndexes eReferenceRuleIndex : eReferenceRuleIndexes) {
				if (eReferenceRuleIndex.getEReference() == eContainmentFeature) {
					return eReferenceRuleIndex.getAssignedTargetRuleValueIndexes();
				}
			}
		}
		return null;
	}

	public @NonNull EClass getEClass() {
		return eClass;
	}

	@Override
	public @NonNull String getName() {
		return SerializationUtils.getName(eClass);
	}

	public @NonNull SerializationRule @NonNull [] getSerializationRules() {
		return serializationRules;
	}

	@Override
	public @NonNull String toString() {
		return getName();
	}

	public void toString(@NonNull StringBuilder s, int depth) {
//		s.append("\n  ");;
		s.append(eClass.getEPackage(). getName());
		s.append("::");;
		s.append(eClass.getName());
		s.append(" <=>");;
//		int depth = 2;
//		serializationRules.toString(s, depth);
		s.append(eClass.getEPackage().getName());
		s.append("::");
		s.append(eClass.getName());
	//	boolean isMany = Iterables.size(serializationRules) > 1;
		for (@NonNull SerializationRule serializationRule : serializationRules) {
	//		SerializationRule serializationRuleAnalysis = serializationRule;//.getSerializationRuleAnalysis();
	//		if (isMany) {
				SerializationUtils.appendIndentation(s, depth+1);
	//		}
	//		else {
	//			s.append(" ");
	//		}
//			s.append(serializationRule.getName());
//			s.append(" - ");
		//	serializationRuleAnalysis.toRuleString(s);
				serializationRule.toMatchTermString(s, depth+2);
		}
	}
}