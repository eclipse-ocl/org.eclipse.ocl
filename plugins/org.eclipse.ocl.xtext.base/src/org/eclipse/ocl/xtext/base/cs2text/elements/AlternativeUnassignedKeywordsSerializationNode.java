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
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationLiteralStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.solutions.StaticRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;
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
	private @Nullable RTSerializationStep runtime = null;
	private @Nullable Integer semanticHashCode = null;

	public AlternativeUnassignedKeywordsSerializationNode(@NonNull MultiplicativeCardinality multiplicativeCardinality, @Nullable Iterable<@NonNull String> values) {
		super(multiplicativeCardinality);
		if (values != null) {
			for (@NonNull String value : values) {
				this.values.add(value);
			}
		}
	}

	public void addKeyword(@NonNull Keyword keyword) {
		assert keyword.getCardinality() == null;
		values.add(XtextGrammarUtil.getValue(keyword));
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable MultiplicativeCardinality multiplicativeCardinality) {
		if (multiplicativeCardinality == null) multiplicativeCardinality = this.multiplicativeCardinality;
		return new AlternativeUnassignedKeywordsSerializationNode(multiplicativeCardinality, values);
	}

	@Override
	public void gatherSteps(@NonNull StaticRuleMatch staticRuleMatch, @NonNull List<@NonNull RTSerializationStep> stepsList) {
		stepsList.add(new RTSerializationLiteralStep(staticRuleMatch.getCardinalityVariableIndex(this), values.get(0)));
	}

	@Override
	public boolean semanticEquals(@NonNull SerializationNode serializationNode) {
		if (serializationNode == this) {
			return true;
		}
		if (!(serializationNode instanceof AlternativeUnassignedKeywordsSerializationNode)) {
			return false;
		}
		AlternativeUnassignedKeywordsSerializationNode that = (AlternativeUnassignedKeywordsSerializationNode)serializationNode;
		if (!this.values.equals(that.values)) {
			return false;
		}
		return true;
	}

	@Override
	public int semanticHashCode() {
		if (semanticHashCode == null) {
			int hash = getClass().hashCode() + values.hashCode();
			semanticHashCode = hash;
		}
		assert semanticHashCode != null;
		return semanticHashCode.intValue();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		boolean isFirst = true;
		if (values.size() > 1) {
			s.append("{");
		}
		for (@NonNull String value : values) {
			if (!isFirst) {
				s.append("|");
			}
			s.append("'");
			s.append(Strings.convertToJavaString(value));
			s.append("'");
			isFirst = false;
		}
		if (values.size() > 1) {
			s.append("}");
		}
		appendCardinality(s, depth);
	}
}