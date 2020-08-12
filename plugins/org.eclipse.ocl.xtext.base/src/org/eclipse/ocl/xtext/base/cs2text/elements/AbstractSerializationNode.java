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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.solutions.StaticRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.CompoundElement;

public abstract class AbstractSerializationNode extends AbstractSerializationElement implements SerializationNode
{
	protected final @NonNull MultiplicativeCardinality multiplicativeCardinality;
	private @Nullable RTSerializationStep runtime = null;

	protected AbstractSerializationNode(@NonNull MultiplicativeCardinality multiplicativeCardinality) {
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

//	protected abstract @NonNull RTSerializationStep createRuntime(@NonNull StaticRuleMatch staticRuleMatch);

	@Override
	public @NonNull SerializationNode freezeAlternatives(@NonNull Alternatives alternatives) {
		return this;
	}

	@Override
	public @NonNull SerializationElement freezeSequences(@NonNull CompoundElement compoundElement, @NonNull MultiplicativeCardinality multiplicativeCardinality) { // XXX is this needed ?
	//	return new SequenceSerializationNode(grammarAnalysis, compoundElement, MultiplicativeCardinality.toEnum(compoundElement), Collections.singletonList(this));
		return createFrozenSequence(compoundElement, multiplicativeCardinality, Collections.singletonList(this));
	}

//	@Override
//	public void gatherRuntime(@NonNull StaticRuleMatch staticRuleMatch, @NonNull List<@NonNull RTSerializationStep> steps) {
//		steps.add(getRuntime(staticRuleMatch));
//	}

	@Override
	public @NonNull MultiplicativeCardinality getMultiplicativeCardinality() {
		return multiplicativeCardinality;
	}

	final protected @NonNull RTSerializationStep getRuntime(@NonNull StaticRuleMatch staticRuleMatch) {
		return ClassUtil.nonNullState(runtime);
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
	public boolean isRedundant() {
		return false;
	}

	@Override
	public void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		StaticRuleMatch staticRuleMatch = serializer.getDynamicRuleMatch().getStaticRuleMatch();
		getRuntime(staticRuleMatch).serialize(serializer, serializationBuilder);
	}

	@Override
	public @NonNull SerializationNode setMultiplicativeCardinality(@NonNull CompoundElement compoundElement, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		if (this.multiplicativeCardinality.isZeroOrMore()) {
			return this;
		}
		return clone(multiplicativeCardinality);
	}
}