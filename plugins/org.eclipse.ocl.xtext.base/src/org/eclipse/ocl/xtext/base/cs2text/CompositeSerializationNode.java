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

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;

public abstract class CompositeSerializationNode extends AbstractSerializationNode
{
	public CompositeSerializationNode(@NonNull XtextParserRuleAnalysis ruleAnalysis, /*@NonNull CompoundElement compoundElement,*/ @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		super(ruleAnalysis, multiplicativeCardinality);
//		this.compoundElement = compoundElement;
	//	assert serializationNodes.size() > 1;
	}

	protected @NonNull RequiredSlots createRequiredSlots(@NonNull List<@NonNull RequiredSlotsConjunction> outerDisjunctions) {
		//
		//	No alternatives => a Conjunction
		//
		switch (outerDisjunctions.size()) {
			case 0: return ruleAnalysis.getNullRequiredSlots();
			case 1: return outerDisjunctions.get(0);
			default: return new RequiredSlotsDisjunction(ruleAnalysis, this, outerDisjunctions);
		}
	}
}