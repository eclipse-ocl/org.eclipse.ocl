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
import org.eclipse.xtext.RuleCall;

public class UnassignedRuleCallSerializationNode extends SimpleSerializationNode
{
	protected final @NonNull XtextAbstractRuleAnalysis calledRuleAnalysis;

	public UnassignedRuleCallSerializationNode(@NonNull XtextParserRuleAnalysis ruleAnalysis, @NonNull RuleCall ruleCall,@NonNull XtextAbstractRuleAnalysis calledRuleAnalysis) {
		super(ruleAnalysis, MultiplicativeCardinality.toEnum(ruleCall.getCardinality()));
		this.calledRuleAnalysis = calledRuleAnalysis;
	}

	@Override
	public @NonNull MultiplicativeCardinality getMultiplicativeCardinality() {
		return MultiplicativeCardinality.ONE;			// ?? could more accurately be ZERO or ONE
	}

	@Override
	public void preSerialize(@NonNull PreSerializer preSerializer) {
		if (!multiplicativeCardinality.mayBeZero()) {
			super.preSerialize(preSerializer);
		}
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(calledRuleAnalysis.getRuleName());
		appendCardinality(s, depth);
	}
}