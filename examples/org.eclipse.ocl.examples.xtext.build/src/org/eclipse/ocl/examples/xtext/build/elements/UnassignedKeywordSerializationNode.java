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

import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.build.analysis.SerializationRuleAnalysis;
import org.eclipse.ocl.examples.xtext.idioms.SubIdiom;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepKeyword;
import org.eclipse.ocl.examples.xtext.serializer.SerializationUtils;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.util.Strings;

public class UnassignedKeywordSerializationNode extends SimpleSerializationNode
{
	protected final @NonNull Keyword keyword;
	protected final @NonNull String value;
//	private @Nullable Integer semanticHashCode = null;

	public UnassignedKeywordSerializationNode(@NonNull Keyword keyword, @NonNull GrammarCardinality grammarCardinality) {
		super(grammarCardinality);
		this.keyword = keyword;
		this.value = SerializationUtils.getValue(keyword);
		assert !grammarCardinality.mayBeZero();
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable GrammarCardinality grammarCardinality) {
		if (grammarCardinality == null) grammarCardinality = this.grammarCardinality;
		return new UnassignedKeywordSerializationNode(keyword, grammarCardinality);
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

/*	@Override
	public boolean semanticEquals(@NonNull SerializationNode serializationNode) {
		if (serializationNode == this) {
			return true;
		}
		if (!(serializationNode instanceof UnassignedKeywordSerializationNode)) {
			return false;
		}
		UnassignedKeywordSerializationNode that = (UnassignedKeywordSerializationNode)serializationNode;
		if (!this.value.equals(that.value)) {
			return false;
		}
		return true;
	} */

/*	@Override
	public int semanticHashCode() {
		if (semanticHashCode == null) {
			int hash = getClass().hashCode() + value.hashCode();
			semanticHashCode = hash;
		}
		assert semanticHashCode != null;
		return semanticHashCode.intValue();
	} */

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("\"");
		s.append(Strings.convertToJavaString(value));
		s.append("\"");
		appendCardinality(s, depth);
	}
}