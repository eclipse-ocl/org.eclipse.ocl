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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.CompoundElement;

public abstract class AbstractSerializationNode extends AbstractSerializationElement implements SerializationNode
{
	/**
	 * The overall (multi-)grammar analysis.
	 */
	protected final @NonNull GrammarAnalysis grammarAnalysis;
	protected final @NonNull MultiplicativeCardinality multiplicativeCardinality;

	protected AbstractSerializationNode(@NonNull GrammarAnalysis grammarAnalysis, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		this.grammarAnalysis = grammarAnalysis;
		this.multiplicativeCardinality = multiplicativeCardinality;
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

	@Override
	public void analyze(@NonNull BasicSerializationRule serializationRule, @NonNull Stack<@NonNull SerializationNode> parentStack) {
		serializationRule.addSerializedNode(this, parentStack);
	}

	protected void appendCardinality(@NonNull StringBuilder s, int depth) {
		if ((depth >= 0) || !multiplicativeCardinality.isOne()) {
			s.append("[");
			s.append(multiplicativeCardinality);
			s.append("]");
		}
	}

	@Override
	public @NonNull SerializationNode asNode() {
		return this;
	}

	@Override
	public @NonNull SerializationNode freezeAlternatives(@NonNull GrammarAnalysis grammarAnalysis, @NonNull Alternatives alternatives) {
		return this;
	}

	@Override
	public @NonNull SerializationElement freezeSequences(@NonNull GrammarAnalysis grammarAnalysis, @NonNull CompoundElement compoundElement, @NonNull MultiplicativeCardinality multiplicativeCardinality) { // XXX is this needed ?
	//	return new SequenceSerializationNode(grammarAnalysis, compoundElement, MultiplicativeCardinality.toEnum(compoundElement), Collections.singletonList(this));
		return createFrozenSequence(grammarAnalysis, compoundElement, multiplicativeCardinality, Collections.singletonList(this));
	}

	@Override
	public @NonNull MultiplicativeCardinality getMultiplicativeCardinality() {
		return multiplicativeCardinality;
	}

	@Override
	public @Nullable AbstractRuleAnalysis getRuleAnalysis() {
		return null;
	}

	@Override
	public boolean isNode() {
		return true;
	}

	@Override
	public boolean isOne() {
		return multiplicativeCardinality.isOne();
	}

	@Override
	public @NonNull SerializationNode setMultiplicativeCardinality(@NonNull GrammarAnalysis grammarAnalysis, @NonNull CompoundElement compoundElement, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		if (this.multiplicativeCardinality.isZeroOrMore()) {
			return this;
		}
		return clone(multiplicativeCardinality);
	}
}