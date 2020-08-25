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
package org.eclipse.ocl.xtext.base.cs2text.xtext;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.user.DynamicSerializationRules;

public class EClassData implements Nameable
{
	protected final @NonNull EClass eClass;
	protected final @NonNull SerializationRule @NonNull [] serializationRules;
	protected final @NonNull EReferenceData @Nullable [] eReferenceDatas;
//	private @Nullable Map<@NonNull EReference, @NonNull IndexVector> eReference2discriminatingRuleValueIndexes = null;	// ?? does this do anything ??

	public EClassData(/*@NonNull*/ EClass eClass, @NonNull SerializationRule @NonNull [] serializationRules,
			@NonNull EReferenceData @Nullable [] eReferenceDatas) {
		assert eClass != null;
		this.eClass = eClass;
		this.serializationRules = serializationRules;
		this.eReferenceDatas = eReferenceDatas;
	}

	public @NonNull EReferenceData @Nullable [] basicGetEReferenceDatas() {
		return eReferenceDatas;
	}

	public @NonNull DynamicSerializationRules createDynamicSerializationRules(@Nullable IndexVector targetRuleValueIndexes) {
		if (targetRuleValueIndexes == null)  {
			return new DynamicSerializationRules(this, serializationRules);
		}
		List<@NonNull SerializationRule> newSerializationRules = new ArrayList<>();
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			int ruleValueIndex = serializationRule.getRuleValueIndex();
			if (targetRuleValueIndexes.test(ruleValueIndex)) {
				newSerializationRules.add(serializationRule);
			/*	Map<@NonNull EReference, @NonNull IndexVector> ruleDiscriminatingEReferences9 = serializationRule.getEReference2DiscriminatingRuleValueIndexes();
				if (ruleDiscriminatingEReferences9 != null) {
					Map<@NonNull EReference, @NonNull IndexVector> eReference2discriminatingRuleValueIndexes2 = eReference2discriminatingRuleValueIndexes;
					if (eReference2discriminatingRuleValueIndexes2 == null) {
						eReference2discriminatingRuleValueIndexes = eReference2discriminatingRuleValueIndexes2 = new HashMap<>();
					}
					for (Map.Entry<@NonNull EReference, @NonNull IndexVector> entry : ruleDiscriminatingEReferences9.entrySet()) {
						EReference eReference = entry.getKey();
						IndexVector discriminatingRuleValueIndexes = eReference2discriminatingRuleValueIndexes2.get(eReference);
						if (discriminatingRuleValueIndexes == null) {
							discriminatingRuleValueIndexes = new IndexVector();
							assert eReference.isOrdered();
							eReference2discriminatingRuleValueIndexes2.put(eReference, discriminatingRuleValueIndexes);
						}
						discriminatingRuleValueIndexes.setAll(entry.getValue());
					}
				} */
			}
		}
		return new DynamicSerializationRules(this, newSerializationRules.toArray(new @NonNull SerializationRule[newSerializationRules.size()]));
	}

	/**
	 * Return the rule analyses assigned by one or more of the serialization rules that can assign eContainmentFeature.
	 */
	public @Nullable IndexVector getAssignedTargetRuleValues(@NonNull EReference eContainmentFeature) {
	/*	Set<@NonNull AbstractRuleValue> targetRuleValues = new HashSet<>();
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
		if (eReferenceDatas != null) {
			for (@NonNull EReferenceData eReferenceData : eReferenceDatas) {
				if (eReferenceData.getEReference() == eContainmentFeature) {
					return eReferenceData.getAssignedTargetRuleValues();
				}
			}
		}
		return null;
	}
/*	public @NonNull IndexVector getAssignedTargetRuleValueIndexes(@NonNull EReference eContainmentFeature) {
		IndexVector targetRuleValueIndexes = new IndexVector();
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			Iterable<@NonNull AssignedSerializationNode> assignedSerializationNodes = serializationRule.getAssignedSerializationNodes(eContainmentFeature);
			if (assignedSerializationNodes != null) {
				for (@NonNull AssignedSerializationNode assignedSerializationNode : assignedSerializationNodes) {
					for (@NonNull AbstractRuleAnalysis targetRuleAnalysis : assignedSerializationNode.getAssignmentAnalysis().getTargetRuleAnalyses()) {
						targetRuleValueIndexes.set(targetRuleAnalysis.getIndex());
					}
				}
			}
		}
		return targetRuleValueIndexes;
	} */

	public @NonNull EClass getEClass() {
		return eClass;
	}

	@Override
	public @NonNull String getName() {
		return XtextGrammarUtil.getName(eClass);
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
		if ("PackageCS".equals(eClass.getName())) {
			getClass(); // XXX debugging
		}
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
				StringUtil.appendIndentation(s, depth+1);
	//		}
	//		else {
	//			s.append(" ");
	//		}
//			s.append(serializationRule.getName());
//			s.append(" - ");
		//	serializationRuleAnalysis.toRuleString(s);
			serializationRule.toSolutionString(s, depth+2);
		}
	}
}