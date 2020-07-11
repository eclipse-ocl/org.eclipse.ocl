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

import org.eclipse.jdt.annotation.NonNull;

public abstract class AbstractSerializationNode implements SerializationNode
{
	/**
	 * The overall (multi-)grammar analysis.
	 */
	protected final @NonNull XtextParserRuleAnalysis ruleAnalysis;
	protected final @NonNull MultiplicativeCardinality multiplicativeCardinality;

	public AbstractSerializationNode(@NonNull XtextParserRuleAnalysis ruleAnalysis, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		this.ruleAnalysis = ruleAnalysis;
		this.multiplicativeCardinality = multiplicativeCardinality;
	}

	protected void appendCardinality(@NonNull StringBuilder s, int depth) {
		if ((depth >= 0) || !multiplicativeCardinality.isOne()) {
			s.append("[");
			s.append(multiplicativeCardinality);
			s.append("]");
		}
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
	public boolean isNull() {
		return false;
	}

	@Override
	public void serialize(@NonNull Serializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		serializationBuilder.append("«Unsupported serialize '" + getClass().getSimpleName() + "'»");
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}
}