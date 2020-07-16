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
package org.eclipse.ocl.xtext.base.cs2text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.CompoundElement;

public abstract class AbstractSerializationNode extends AbstractSerializationElement implements SerializationNode
{
	/**
	 * The overall (multi-)grammar analysis.
	 */
	protected final @NonNull XtextParserRuleAnalysis ruleAnalysis;
	protected final @NonNull MultiplicativeCardinality multiplicativeCardinality;

	protected AbstractSerializationNode(@NonNull XtextParserRuleAnalysis ruleAnalysis, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		this.ruleAnalysis = ruleAnalysis;
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
			return new ListOfSerializationNode(newList, MultiplicativeCardinality.ONE);
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
	public @NonNull SerializationNode freezeAlternatives(@NonNull XtextParserRuleAnalysis ruleAnalysis, @NonNull Alternatives alternatives) {
		return this;
	}

	@Override
	public @NonNull SerializationElement freezeSequences(@NonNull XtextParserRuleAnalysis ruleAnalysis, @NonNull CompoundElement compoundElement) {
		return new SequenceSerializationNode(ruleAnalysis, compoundElement, MultiplicativeCardinality.toEnum(compoundElement), Collections.singletonList(this));
	}

	@Override
	public @NonNull MultiplicativeCardinality getMultiplicativeCardinality() {
		return multiplicativeCardinality;
	}

	@Override
	public @NonNull XtextParserRuleAnalysis getRuleAnalysis() {
		return ruleAnalysis;
	}

	@Override
	public boolean isNode() {
		return true;
	}

	@Override
	public @NonNull SerializationElement setMultiplicativeCardinality(@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		if (multiplicativeCardinality.isZeroOrMore()) {
			return this;
		}
		return clone(multiplicativeCardinality);
	}
}