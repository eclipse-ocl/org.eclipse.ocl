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

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.runtime.GrammarCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep.SerializationStepLiteral;
import org.eclipse.ocl.xtext.base.cs2text.solutions.StaticRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.util.Strings;

public class UnassignedKeywordSerializationNode extends SimpleSerializationNode
{
	protected final @NonNull Keyword keyword;
	protected final @NonNull String value;
	private @Nullable Integer semanticHashCode = null;

	public UnassignedKeywordSerializationNode(@NonNull Keyword keyword, @NonNull GrammarCardinality multiplicativeCardinality) {
		super(multiplicativeCardinality);
		this.keyword = keyword;
		this.value = XtextGrammarUtil.getValue(keyword);
		assert !multiplicativeCardinality.mayBeZero();
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable GrammarCardinality multiplicativeCardinality) {
		if (multiplicativeCardinality == null) multiplicativeCardinality = this.multiplicativeCardinality;
		return new UnassignedKeywordSerializationNode(keyword, multiplicativeCardinality);
	}

	@Override
	public void gatherSteps(@NonNull StaticRuleMatch staticRuleMatch, @NonNull List<@NonNull SerializationStep> stepsList) {
		stepsList.add(new SerializationStepLiteral(staticRuleMatch.getCardinalityVariableIndex(this), value));
	}

	public @NonNull String getValue() {
		return value;
	}

	@Override
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
	}

	@Override
	public int semanticHashCode() {
		if (semanticHashCode == null) {
			int hash = getClass().hashCode() + value.hashCode();
			semanticHashCode = hash;
		}
		assert semanticHashCode != null;
		return semanticHashCode.intValue();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("'");
		s.append(Strings.convertToJavaString(value));
		s.append("'");
		appendCardinality(s, depth);
	}
}