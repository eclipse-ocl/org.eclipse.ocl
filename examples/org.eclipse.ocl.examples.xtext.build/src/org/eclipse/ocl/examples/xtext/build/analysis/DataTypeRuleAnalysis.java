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
package org.eclipse.ocl.examples.xtext.build.analysis;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.serializer.DataTypeRuleValue;
import org.eclipse.xtext.ParserRule;

/**
 * An DataTypeRuleAnalysis provides the extended analysis of a ParserRule for a DataType
 */
public class DataTypeRuleAnalysis extends AbstractRuleAnalysis
{
	protected final @NonNull EDataType eDataType;
	private @Nullable DataTypeRuleValue dataTypeRuleValue = null;

	public DataTypeRuleAnalysis(@NonNull GrammarAnalysis grammarAnalysis, int index, @NonNull ParserRule parserRule, @NonNull EDataType eDataType) {
		super(grammarAnalysis, index, parserRule);
		this.eDataType = eDataType;
	}

	@Override
	public @Nullable DataTypeRuleValue basicGetRuleValue() {
		return dataTypeRuleValue;
	}

	@Override
	public @NonNull DataTypeRuleValue getRuleValue() {
		DataTypeRuleValue dataTypeRuleValue2 = dataTypeRuleValue;
		if (dataTypeRuleValue2 == null) {
			dataTypeRuleValue = dataTypeRuleValue2 = new DataTypeRuleValue(index, getName());
		}
		return dataTypeRuleValue2;
	}
}