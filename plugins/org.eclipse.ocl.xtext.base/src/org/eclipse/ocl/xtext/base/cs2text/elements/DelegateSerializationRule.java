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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;

public class DelegateSerializationRule extends AbstractSerializationRule
{
	protected final @NonNull BasicSerializationRule delegatedSerializationRule;

	public DelegateSerializationRule(@NonNull ParserRuleAnalysis ruleAnalysis, @NonNull BasicSerializationRule delegatedSerializationRule) {
		super(ruleAnalysis, delegatedSerializationRule.getRootSerializationNode());
		this.delegatedSerializationRule = delegatedSerializationRule;
	}

	@Override
	public @NonNull BasicSerializationRule getBasicSerializationRule() {
		return delegatedSerializationRule;
	}

	@Override
	public void toSolutionString(@NonNull StringBuilder s, int depth) {
	//	if (preSerializer != null) {
	//		preSerializer.toString(s, 0);
	//	}
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(ruleAnalysis.getName());
		s.append(" => ");
		delegatedSerializationRule.toString(s, depth);
	}
}