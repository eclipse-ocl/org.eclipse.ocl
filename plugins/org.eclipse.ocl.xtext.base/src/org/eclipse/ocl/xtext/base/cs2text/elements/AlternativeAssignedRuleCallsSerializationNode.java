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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.Serializer;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.DirectAssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;
import org.eclipse.xtext.conversion.IValueConverterService;

/**
 * An AlternativeAssignedKeywordsSerializationNode corresponds to the parsing specification of a variety of keywords
 * one of which is assigned to a String feature in the parsed model. The alternative keywords behave as an enumeration.
 * Multiple AlternativeAssignedKeywordsSerializationNode for the same feature may define orthogonal or overlapping
 * enumerations.
 */
public class AlternativeAssignedRuleCallsSerializationNode extends AbstractAssignedSerializationNode
{
	protected final @NonNull Iterable<@NonNull AbstractRuleAnalysis> calledRuleAnalyses;

	private @NonNull IValueConverterService valueConverterService;

	public AlternativeAssignedRuleCallsSerializationNode(@NonNull DirectAssignmentAnalysis assignmentAnalysis,
			@NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull Iterable<@NonNull AbstractRuleAnalysis> calledRuleAnalyses) {
		super(assignmentAnalysis, multiplicativeCardinality);
		this.calledRuleAnalyses = calledRuleAnalyses;
		this.valueConverterService = grammarAnalysis.getValueConverterService();
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable MultiplicativeCardinality multiplicativeCardinality) {
		if (multiplicativeCardinality == null) multiplicativeCardinality = this.multiplicativeCardinality;
		return new AlternativeAssignedRuleCallsSerializationNode((DirectAssignmentAnalysis)assignmentAnalysis, multiplicativeCardinality, calledRuleAnalyses);
	}

	@Override
	public @Nullable Iterable<@NonNull AbstractRuleAnalysis> getAssignedRuleAnalyses() {
		return calledRuleAnalyses;
	}

	@Override
	public void serialize(@NonNull Serializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		Object eGet = serializer.consumeNext(eStructuralFeature);
		AbstractRuleAnalysis calledRuleAnalysis = calledRuleAnalyses.iterator().next();		// XXX search for matching rule
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
		s.append("(");
		boolean isFirst = true;
		for (@NonNull AbstractRuleAnalysis calledRuleAnalysis : calledRuleAnalyses) {
			if (!isFirst) {
				s.append("|");
			}
			s.append(calledRuleAnalysis.getRuleName());
			isFirst = false;
		}
		s.append(")");
		appendCardinality(s, depth);
	}
}