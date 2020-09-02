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
package org.eclipse.ocl.xtext.base.cs2text.runtime;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Segment;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRule.EReference_RuleIndexes;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;

public class EClassValue implements Nameable
{
	public static class SerializationRule_SegmentsList //implements Nameable
	{
		protected final @NonNull SerializationRule serializationRule;
		protected final @NonNull Segment @NonNull [] @Nullable [] staticSegments;

		public SerializationRule_SegmentsList(@NonNull SerializationRule serializationRule, @NonNull Segment @NonNull [] @Nullable [] staticSegments) {
			this.serializationRule = serializationRule;
			this.staticSegments = staticSegments;
		}

	//	@Override
	//	public @NonNull String getName() {
	//		return XtextGrammarUtil.getName(eReference);
	//	}

		public @NonNull SerializationRule getSerializationRule() {
			return serializationRule;
		}

		public @NonNull Segment @NonNull [] @Nullable [] getStaticSegments() {
			return staticSegments;
		}

	//	@Override
	//	public @NonNull String toString() {
	//		return eReference.getEContainingClass().getName() + "::" + eReference.getName() + " " + parserRuleValueIndexes;
	//	}
	}
	protected final @NonNull EClass eClass;
	protected final @NonNull SerializationRule_SegmentsList @NonNull [] serializationRuleSegmentsLists;
	protected final @NonNull EReference_RuleIndexes @Nullable [] eReferenceRuleIndexes;

	public EClassValue(/*@NonNull*/ EClass eClass, @NonNull SerializationRule_SegmentsList @NonNull [] serializationRuleSegmentsLists,
			@NonNull EReference_RuleIndexes @Nullable [] eReferenceRuleIndexes) {
		assert eClass != null;
		this.eClass = eClass;
		this.serializationRuleSegmentsLists = serializationRuleSegmentsLists;
		this.eReferenceRuleIndexes = eReferenceRuleIndexes;
	}

	public @NonNull EReference_RuleIndexes @Nullable [] basicGetEReferenceRuleIndexes() {
		return eReferenceRuleIndexes;
	}

	public @NonNull DynamicSerializationRules createDynamicSerializationRules(@Nullable GrammarRuleVector targetRuleValueIndexes) {
		if (targetRuleValueIndexes == null)  {
			return new DynamicSerializationRules(this, serializationRuleSegmentsLists);
		}
		List<@NonNull SerializationRule_SegmentsList> newSerializationRuleSegmentsLists = new ArrayList<>();
		for (@NonNull SerializationRule_SegmentsList serializationRuleSegmentsList : serializationRuleSegmentsLists) {
			SerializationRule serializationRule = serializationRuleSegmentsList.getSerializationRule();
			int ruleValueIndex = serializationRule.getRuleValueIndex();
			if (targetRuleValueIndexes.test(ruleValueIndex)) {
				newSerializationRuleSegmentsLists.add(serializationRuleSegmentsList);
			}
		}
		return new DynamicSerializationRules(this, newSerializationRuleSegmentsLists.toArray(new @NonNull SerializationRule_SegmentsList[newSerializationRuleSegmentsLists.size()]));
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
		return XtextGrammarUtil.getName(eClass);
	}

	public @NonNull SerializationRule_SegmentsList @NonNull [] getSerializationRuleSegmentsLists() {
		return serializationRuleSegmentsLists;
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
		for (@NonNull SerializationRule_SegmentsList serializationRuleSegmentsList : serializationRuleSegmentsLists) {
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
				serializationRuleSegmentsList.getSerializationRule().toSolutionString(s, depth+2);
		}
	}
}