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

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule;

/**
 * The (static) SerializationRules identify the alternative rules that may be used to serialize a given EClass.
 * Once the actual EObject instance of EClass is known a DynamicSerializationRules identifies the subset of the
 * rules with compatiible containment ancestry.
 */
public class SerializationRules extends RTSerializationRules
{
	private @Nullable Map<@NonNull EReference, @NonNull List<@NonNull ParserRuleAnalysis>> eReference2disciminatingRuleAnalyses = null;

	public SerializationRules(/*@NonNull*/ EClass eClass, @NonNull Iterable<@NonNull RTSerializationRule> serializationRules) {
		super(eClass, serializationRules);
		assert eClass != null;
	}

	public @Nullable Map<@NonNull EReference, @NonNull List<@NonNull ParserRuleAnalysis>> getEReference2disciminatedRuleAnalyses() {
		return eReference2disciminatingRuleAnalyses;
	}
}