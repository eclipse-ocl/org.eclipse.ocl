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
package org.eclipse.ocl.xtext.base.cs2text.xtext;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.xtext.ParserRule;

/**
 * An DataTypeRuleAnalysis provides the extended analysis of a ParserRule for a DataType
 */
public class DataTypeRuleAnalysis extends AbstractRuleAnalysis
{
	protected final @NonNull EDataType eDataType;

	public DataTypeRuleAnalysis(@NonNull GrammarAnalysis grammarAnalysis, @NonNull ParserRule parserRule, @NonNull EDataType eDataType) {
		super(grammarAnalysis, parserRule);
		this.eDataType = eDataType;
	}
}