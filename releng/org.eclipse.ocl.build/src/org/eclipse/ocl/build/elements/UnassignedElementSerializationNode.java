/*******************************************************************************
 * Copyright (c) 2020, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.build.elements;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.build.analysis.SerializationRuleAnalysis;
import org.eclipse.ocl.xtext.base.serializer.DiagnosticStringBuilder;
import org.eclipse.ocl.xtext.base.serializer.GrammarCardinality;
import org.eclipse.ocl.xtext.base.serializer.SerializationSegment;
import org.eclipse.ocl.xtext.base.serializer.SerializationStep;
import org.eclipse.ocl.xtext.base.serializer.SerializationStep.SerializationStepKeyword;
import org.eclipse.ocl.xtext.idioms.SubIdiom;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.util.Strings;

public class UnassignedElementSerializationNode extends AbstractUnassignedSerializationNode
{
	protected final @NonNull AbstractElement keyword;
	protected final @NonNull String value;

	public UnassignedElementSerializationNode(@NonNull AbstractElement keyword, @NonNull EClassifier producedEClassifier, @NonNull GrammarCardinality grammarCardinality) {
		super(producedEClassifier, grammarCardinality);
		this.keyword = keyword;
		this.value = keyword.eClass().getName() + "@" + Integer.toHexString(System.identityHashCode(keyword));
	//	assert !grammarCardinality.mayBeZero();
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable GrammarCardinality grammarCardinality) {
		if (grammarCardinality == null) grammarCardinality = this.grammarCardinality;
		return new UnassignedElementSerializationNode(keyword, producedEClassifier, grammarCardinality);
	}

	@Override
	public void gatherStepsAndSubIdioms(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, @NonNull List<@NonNull SerializationStep> stepsList,
			@NonNull Map<@NonNull SerializationNode, @NonNull List<@NonNull SubIdiom>> serializationNode2subIdioms) {
		@NonNull SerializationSegment @Nullable [] eachSerializationSegments = gatherStepsAndSubIdiomsAll(serializationRuleAnalysis, stepsList, serializationNode2subIdioms);
		stepsList.add(new SerializationStepKeyword(value, eachSerializationSegments));
	}

	public @NonNull String getValue() {
		return value;
	}

	@Override
	public void toString(@NonNull DiagnosticStringBuilder s, int depth) {
		s.append("\"");
		s.appendObject(Strings.convertToJavaString(value));
		s.append("\"");
		appendCardinality(s, depth);
	}
}