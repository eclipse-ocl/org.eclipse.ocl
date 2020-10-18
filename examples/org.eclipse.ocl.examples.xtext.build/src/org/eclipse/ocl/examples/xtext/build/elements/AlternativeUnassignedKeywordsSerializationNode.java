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
package org.eclipse.ocl.examples.xtext.build.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.build.analysis.SerializationRuleAnalysis;
import org.eclipse.ocl.examples.xtext.idioms.SubIdiom;
import org.eclipse.ocl.examples.xtext.serializer.DiagnosticStringBuilder;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepKeyword;
import org.eclipse.ocl.examples.xtext.serializer.SerializationUtils;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.util.Strings;

/**
 * An AlternativeUnassignedKeywordsSerializationNode corresponds to the parsing specification of a variety of keywords
 * none of which are assigned to the parsed model, consequently the serialization cannot know which to use. The construct
 * is therefore redundant and the first keyword is always used for serialization.
 */
public class AlternativeUnassignedKeywordsSerializationNode extends AbstractSerializationNode
{
	protected final @NonNull List<@NonNull String> values = new ArrayList<>();

	public AlternativeUnassignedKeywordsSerializationNode(@NonNull GrammarCardinality grammarCardinality, @Nullable Iterable<@NonNull String> values) {
		super(grammarCardinality);
		if (values != null) {
			for (@NonNull String value : values) {
				this.values.add(value);
			}
		}
	}

	public void addKeyword(@NonNull Keyword keyword) {
		assert keyword.getCardinality() == null;
		values.add(SerializationUtils.getValue(keyword));
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable GrammarCardinality grammarCardinality) {
		if (grammarCardinality == null) grammarCardinality = this.grammarCardinality;
		return new AlternativeUnassignedKeywordsSerializationNode(grammarCardinality, values);
	}

	@Override
	public void gatherStepsAndSubIdioms(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, @NonNull List<@NonNull SerializationStep> stepsList,
			@NonNull Map<@NonNull SerializationNode, @NonNull List<@NonNull SubIdiom>> serializationNode2subIdioms) {
		@NonNull SerializationSegment @Nullable [] eachSerializationSegments = gatherStepsAndSubIdiomsAll(serializationRuleAnalysis, stepsList, serializationNode2subIdioms);
		stepsList.add(new SerializationStepKeyword(values.get(0), eachSerializationSegments));
	}

	@Override
	public void toString(@NonNull DiagnosticStringBuilder s, int depth) {
		boolean isFirst = true;
		if (values.size() > 1) {
			s.append("{");
		}
		for (@NonNull String value : values) {
			if (!isFirst) {
				s.append("|");
			}
			s.append("\"");
			s.appendObject(Strings.convertToJavaString(value));
			s.append("\"");
			isFirst = false;
		}
		if (values.size() > 1) {
			s.append("}");
		}
		appendCardinality(s, depth);
	}
}