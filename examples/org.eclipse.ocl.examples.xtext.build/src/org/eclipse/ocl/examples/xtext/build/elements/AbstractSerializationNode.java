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
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.build.analysis.GrammarAnalysis;
import org.eclipse.ocl.examples.xtext.build.analysis.SerializationRuleAnalysis;
import org.eclipse.ocl.examples.xtext.idioms.SubIdiom;
import org.eclipse.ocl.examples.xtext.serializer.DiagnosticStringBuilder;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepSequence;
import org.eclipse.ocl.examples.xtext.serializer.SerializationUtils;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.CompoundElement;

public abstract class AbstractSerializationNode extends AbstractSerializationElement implements SerializationNode
{
	protected final @NonNull GrammarCardinality grammarCardinality;

	protected AbstractSerializationNode(@NonNull GrammarCardinality grammarCardinality) {
		this.grammarCardinality = grammarCardinality;
	}

	@Override
	public @NonNull SerializationElement addConcatenation(@NonNull SerializationElement additionalSerializationElement) {
		if (additionalSerializationElement.isNull()) {
			return this;
		}
		else if (additionalSerializationElement.isNode()) {
			List<@NonNull SerializationNode> newList = new ArrayList<>();
			newList.add(this);
			newList.add(additionalSerializationElement.asNode());
			return new ListOfSerializationNode(newList);
		}
		else if (additionalSerializationElement.isList()) {
			throw new IllegalStateException();			// Additional list should not be a future SequenceSerializationNode
		}
		else if (additionalSerializationElement.isListOfList()) {
			for (@NonNull List<@NonNull SerializationNode> additionalList : additionalSerializationElement.asListOfList().getLists()) {
				additionalList.add(0, this);
			}
			return additionalSerializationElement;
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	protected void appendCardinality(@NonNull DiagnosticStringBuilder s, int depth) {
		if ((depth >= 0) || !grammarCardinality.isOne()) {
			s.append("[");
			s.appendObject(grammarCardinality);
			s.append("]");
		}
	}

	@Override
	public @NonNull SerializationNode asNode() {
		return this;
	}

	@Override
	public @NonNull SerializationNode freezeAlternatives(@NonNull Alternatives alternatives) {
		return this;
	}

	@Override
	public @NonNull SerializationElement freezeSequences(@NonNull CompoundElement compoundElement, @NonNull GrammarCardinality grammarCardinality, boolean isRootAlternative) { // is this needed ?
		@SuppressWarnings("null")
		@NonNull List<@NonNull SerializationNode> castSingletonList = Collections.singletonList(this);
		return createFrozenSequence(compoundElement, grammarCardinality, castSingletonList, isRootAlternative);
	}

	protected @NonNull SerializationSegment @Nullable [] gatherStepsAndSubIdiomsAll(@NonNull SerializationRuleAnalysis serializationRuleAnalysis,
			@NonNull List<@NonNull SerializationStep> stepsList, @NonNull Map<@NonNull SerializationNode, @NonNull List<@NonNull SubIdiom>> serializationNode2subIdioms) {
		@NonNull SerializationSegment @Nullable [] allSerializationSegments = null;
		@NonNull SerializationSegment @Nullable [] eachSerializationSegments = null;
		List<@NonNull SubIdiom> subIdioms = SerializationUtils.maybeNull(serializationNode2subIdioms.get(this));
		if (subIdioms != null) {
			GrammarAnalysis grammarAnalysis = serializationRuleAnalysis.getGrammarAnalysis();
			allSerializationSegments = grammarAnalysis.getSerializationSegments(subIdioms, true);
			eachSerializationSegments = grammarAnalysis.getSerializationSegments(subIdioms, false);
		}
		int cardinalityVariableIndex = serializationRuleAnalysis.getCardinalityVariableIndex(this);
		if ((cardinalityVariableIndex >= 0) || (allSerializationSegments != null)) {
			stepsList.add(new SerializationStepSequence(cardinalityVariableIndex, 1, grammarCardinality, allSerializationSegments));
		}
		return eachSerializationSegments;
	}

	@Override
	public @NonNull GrammarCardinality getGrammarCardinality() {
		return grammarCardinality;
	}

	@Override
	public boolean isNode() {
		return true;
	}

	@Override
	public boolean isOne() {
		return grammarCardinality.isOne();
	}

	@Override
	public boolean isRedundant() {
		return false;
	}

	@Override
	public @NonNull SerializationNode setGrammarCardinality(@NonNull CompoundElement compoundElement, @NonNull GrammarCardinality grammarCardinality) {
		if (this.grammarCardinality.isZeroOrMore()) {
			return this;
		}
		return clone(grammarCardinality);
	}
}