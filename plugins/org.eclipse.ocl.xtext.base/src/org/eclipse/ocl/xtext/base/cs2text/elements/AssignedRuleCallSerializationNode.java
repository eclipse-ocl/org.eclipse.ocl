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
package org.eclipse.ocl.xtext.base.cs2text.elements;

import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;
import org.eclipse.xtext.conversion.IValueConverterService;

public class AssignedRuleCallSerializationNode extends AbstractAssignedSerializationNode
{
	protected final @NonNull AbstractRuleAnalysis calledRuleAnalysis;

	private @NonNull IValueConverterService valueConverterService;

	public AssignedRuleCallSerializationNode(@NonNull AssignmentAnalysis assignmentAnalysis, @NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull AbstractRuleAnalysis calledRuleAnalysis) {
		super(assignmentAnalysis, multiplicativeCardinality);
		this.calledRuleAnalysis = calledRuleAnalysis;
		this.valueConverterService = assignmentAnalysis.getGrammarAnalysis().getValueConverterService();
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable MultiplicativeCardinality multiplicativeCardinality) {
		if (multiplicativeCardinality == null) multiplicativeCardinality = this.multiplicativeCardinality;
		return new AssignedRuleCallSerializationNode(assignmentAnalysis, multiplicativeCardinality, calledRuleAnalysis);
	}

	@Override
	public @Nullable Iterable<@NonNull AbstractRuleAnalysis> getAssignedRuleAnalyses() {
		return Collections.singletonList(calledRuleAnalysis);
	}

	@Override
	public void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		Object eGet = serializer.consumeNext(eStructuralFeature);
		if (eStructuralFeature instanceof EReference) {
			assert ((EReference)eStructuralFeature).isContainment();
			if (eGet != null) {
				serializer.serializeElement(serializationBuilder, (EObject)eGet, calledRuleAnalysis);
			}
		}
		else {
			String val = valueConverterService.toString(eGet, calledRuleAnalysis.getRuleName());
			serializationBuilder.append(String.valueOf(val));
		}
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		XtextGrammarUtil.appendEStructuralFeatureName(s, assignmentAnalysis);
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		s.append(calledRuleAnalysis.getRuleName());
		appendCardinality(s, depth);
	}
}