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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.Serializer;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;

import com.google.common.collect.Iterables;

/**
 * A UserElementAnalysis provides the working context to assist in the determination of the Xtext grammar rule
 * that can produce and assign a user model element that has a container.
 */
public class UserElementAnalysis implements Nameable
{
	private static int count = 0;

	protected final @NonNull UserModelAnalysis modelAnalysis;
	protected final @NonNull GrammarAnalysis grammarAnalysis;
	protected final @NonNull EObject eObject;
	protected final @NonNull EClass eClass;
	private final @NonNull String name;
	protected final @Nullable UserElementAnalysis containingElementAnalysis;
	protected final @Nullable EReference eContainmentFeature;
	private @NonNull Iterable<@NonNull SerializationRule> serializationRules;

	public UserElementAnalysis(@NonNull UserModelAnalysis modelAnalysis, @NonNull EObject eObject) {
		assert eObject.eContainer() == null;
		this.modelAnalysis = modelAnalysis;
		this.grammarAnalysis = modelAnalysis.getGrammarAnalysis();
		this.eObject = eObject;
		this.eClass = UserModelAnalysis.eClass(eObject);
		this.name = eClass.getName() + "@" + ++count;
		this.containingElementAnalysis = null;
		this.eContainmentFeature = null;
		this.serializationRules = grammarAnalysis.getSerializationRules(eClass);
	}

	public UserElementAnalysis(@NonNull UserModelAnalysis modelAnalysis, @NonNull UserElementAnalysis containingElementAnalysis,
			@NonNull EReference eContainmentFeature, @NonNull EObject eObject) {
		assert eObject.eContainer() != null;
		this.modelAnalysis = modelAnalysis;
		this.grammarAnalysis = modelAnalysis.getGrammarAnalysis();
		this.eObject = eObject;
		this.eClass = UserModelAnalysis.eClass(eObject);
		this.name = eClass.getName() + "@" + ++count;
		this.containingElementAnalysis = containingElementAnalysis;
		this.eContainmentFeature = eContainmentFeature;
		this.serializationRules = analyzeSerializationRules();
	}

	/**
	 * Determine the rules able to produce this element and the containing assignments by which it can be contained.
	 */
	private @NonNull Iterable<@NonNull SerializationRule> analyzeSerializationRules() {
		String eClassName = eClass.getName();
		if ("SelfExpCS".equals(eClassName)) {
			getClass();				// XXX
		}
		List<@NonNull SerializationRule> serializationRules = new ArrayList<>();
		UserElementAnalysis containingElementAnalysis2 = containingElementAnalysis;
		if (containingElementAnalysis2 != null) {
			Set<AbstractRuleAnalysis> targetRuleAnalyses = new HashSet<>();
			for (@NonNull SerializationRule parentSerializationRule : grammarAnalysis.getSerializationRules(containingElementAnalysis2.getEClass())) {
				assert eContainmentFeature != null;
				Iterable<@NonNull AssignedSerializationNode> assignedSerializationNodes = parentSerializationRule.getAssignedSerializationNodes(eContainmentFeature);
				if (assignedSerializationNodes != null) {
					for (@NonNull AssignedSerializationNode assignedSerializationNode : assignedSerializationNodes) {
						for (@NonNull AbstractRuleAnalysis targetRuleAnalysis : assignedSerializationNode.getAssignmentAnalysis().getTargetRuleAnalyses()) {
							targetRuleAnalyses.add(targetRuleAnalysis);
						}
					}
				}
			}
			for (@NonNull SerializationRule serializationRule : grammarAnalysis.getSerializationRules(eClass)) {
				if (targetRuleAnalyses.contains(serializationRule.getRuleAnalysis())) {
					serializationRules.add(serializationRule);
				}
			}
		}
		return serializationRules;
	}

	public @Nullable Serializer createSerializer(@NonNull UserSlotsAnalysis slotsAnalysis) {
		Iterable<@NonNull SerializationRule> serializationRules = getSerializationRules();
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			BasicSerializationRule basicSerializationRule = serializationRule.getBasicSerializationRule();
			DynamicRuleMatch dynamicRuleMatch = basicSerializationRule.match(slotsAnalysis);
			if (dynamicRuleMatch != null) {
				return new Serializer(dynamicRuleMatch, modelAnalysis, eObject);
			}
		}
		return null;
	}

	public @Nullable UserElementAnalysis getContainingElementAnalysis() {
		return containingElementAnalysis;
	}

	public @NonNull EClass getEClass() {
		return eClass;
	}

	public @NonNull EObject getEObject() {
		return eObject;
	}

	public @NonNull GrammarAnalysis getGrammarAnalysis() {
		return grammarAnalysis;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	public @NonNull Iterable<@NonNull SerializationRule> getSerializationRules() {
		return serializationRules;
	}

	protected @NonNull UserSlotsAnalysis getSlotsAnalysis() {
		return new UserSlotsAnalysis(getSerializationRules(), eObject);
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(getName());
		s.append(" <=>");
		Iterable<@NonNull SerializationRule> serializationRules2 = ClassUtil.maybeNull(serializationRules);
		if (serializationRules2 != null) {
			boolean isMany = Iterables.size(serializationRules2) > 1;
			for (@NonNull SerializationRule serializationRule : serializationRules2) {
				BasicSerializationRule basicSerializationRule = serializationRule.getBasicSerializationRule();
				if (isMany) {
					StringUtil.appendIndentation(s, depth+1, "  ");
				}
				else {
					s.append(" ");
				}
				s.append(serializationRule.getName());
				s.append(" - ");
				basicSerializationRule.toRuleString(s);
				basicSerializationRule.toSolutionString(s, depth+2);
			}
		}
	}
}